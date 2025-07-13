package com.pmb.auth.presentation.ekyc.ekyc_video_capture.viewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.ekyc.capture_video.entity.CapturingVideoParams
import com.pmb.auth.domain.ekyc.capture_video.useCase.SendVideoUseCase
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerEvent
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerState
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerStatus
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerTypeId
import com.pmb.auth.utils.startCountUp
import com.pmb.camera.platform.VideoCameraManagerImpl
import com.pmb.camera.platform.VideoViewActions
import com.pmb.compressor.compression.VideoCompressor
import com.pmb.compressor.compression.VideoQuality
import com.pmb.compressor.config.Configuration
import com.pmb.compressor.listeners.CompressionListener
import com.pmb.core.fileManager.FileManager
import com.pmb.core.permissions.PermissionDispatcher
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EKYCAuthenticationCapturingVideoViewModel @Inject constructor(
    initialState: EKYCAuthenticationCapturingVideoViewState,
    private val permissionDispatcher: PermissionDispatcher,
    private val cameraManager: VideoCameraManagerImpl,
    private val videoCompressor: VideoCompressor,
    private val fileManager: FileManager,
    private val sendVideoUseCase: SendVideoUseCase
) : BaseViewModel<
        VideoViewActions,
        EKYCAuthenticationCapturingVideoViewState,
        EKYCAuthenticationCapturingVideoViewEvents>(initialState) {
    override fun handle(action: VideoViewActions) {
        when (action) {
            is VideoViewActions.RequestCameraPermission -> {
                requestCameraPermission(action)
            }

            is VideoViewActions.RequestFilePermission -> requestFilePermission(action)
            is VideoViewActions.RequestAudioPermission -> requestAudioPermission(action)
            is VideoViewActions.VideoCaptured -> videoCaptured()
            is VideoViewActions.CapturingVideo -> videoCapturing()
            is VideoViewActions.ToggleCamera -> toggleCamera()
            is VideoViewActions.PreviewCamera -> {
                previewCamera(action)
            }

            is EKYCAuthenticationCapturingVideoViewActions.ClearVideo -> {
                clearVideo()
            }

            is EKYCAuthenticationCapturingVideoViewActions.SendVideo -> {
                handleSendFacePhoto(action)
            }

            is EKYCAuthenticationCapturingVideoViewActions.FinishTimer -> {
                finishTimer()
            }

            is EKYCAuthenticationCapturingVideoViewActions.ClearAlert -> {
                setState {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    private fun handleSendFacePhoto(action: EKYCAuthenticationCapturingVideoViewActions.SendVideo) {
        viewModelScope.launch {
            sendVideoUseCase.invoke(CapturingVideoParams(action.uri)).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                alertModelState = null,
                                timerState = null,
                                hasCameraPermission = false,
                                hasFilePermissions = false,
                                isCameraReady = false,
                                isFrontCamera = false,
                                isCapturingVideo = false,
                                videoCaptured = false,
                                savedFileUri = null,
                                cameraHasError = null,
                                isCameraLoading = false,
                                isCompressing = false
                            )
                        }
                        postEvent(EKYCAuthenticationCapturingVideoViewEvents.VideoCaptured)
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                alertModelState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                }
            }
        }
    }

    fun onSinglePermissionResult(isGranted: Boolean) {
        permissionDispatcher.onSinglePermissionResult(isGranted)

    }

    fun onMultiplePermissionResult(grantResult: Map<String, Boolean>) {
        permissionDispatcher.onMultiplePermissionResult(grantResult)
    }

    private fun requestCameraPermission(action: VideoViewActions.RequestCameraPermission) {
        viewModelScope.launch {

            permissionDispatcher.initialize(singlePermissionLauncher = action.managedActivityResultLauncher)
            permissionDispatcher.requestSinglePermission(
                permission = android.Manifest.permission.CAMERA,
                onPermissionGranted = {
                    Log.i("per", "You have permission for using camera")
                    setState { state ->
                        state.copy(hasCameraPermission = true)
                    }
                },
                onPermissionDenied = {
                    setState { state ->
                        Log.i("per", "You don't have permission for using camera")
                        state.copy(cameraHasError = "You don't have permission for using camera")
                    }
                }
            )
        }
    }
    private fun requestAudioPermission(action: VideoViewActions.RequestAudioPermission) {
        viewModelScope.launch {

            permissionDispatcher.initialize(singlePermissionLauncher = action.managedActivityResultLauncher)
            permissionDispatcher.requestSinglePermission(
                permission = android.Manifest.permission.RECORD_AUDIO,
                onPermissionGranted = {
                    setState { state ->
                        Log.i("per", "You have permission for using camera")
                        state.copy(hasAudioPermissions = true, hasCameraPermission = true, hasFilePermissions = true)
                    }
                },
                onPermissionDenied = {
                    setState { state ->
                        Log.i("per", "You don't have permission for using camera")
                        state.copy(cameraHasError = "You don't have permission for using camera")
                    }
                }
            )
        }
    }

    private fun requestFilePermission(action: VideoViewActions.RequestFilePermission) {
        viewModelScope.launch {
            permissionDispatcher.initialize(multiplePermissionLauncher = action.managedActivityResultLauncher)
            permissionDispatcher.requestMultiplePermission(
                permissions = arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                    android.Manifest.permission.RECORD_AUDIO
                ),
                onPermissionGranted = {
                    setState { state ->
                        state.copy(hasFilePermissions = true)
                    }
                },
                onPermissionDenied = { deniedPermissions ->
                    setState { state ->
                        state.copy(cameraHasError = "You don't have ${deniedPermissions} for using file explorer")
                    }
                }
            )
        }
    }

    private fun previewCamera(action: VideoViewActions.PreviewCamera) {
        setState { state ->
            state.copy(isLoading = true)
        }
        cameraManager.startCamera(
            previewView = action.previewView,
            lifecycleOwner = action.lifecycleOwner,
            onSuccess = {
                setState { state ->
                    state.copy(
                        isCameraReady = true,
                        isLoading = false,
                        cameraHasError = null
                    )
                }
            },
            onError = { error ->
                setState { state ->
                    state.copy(
                        cameraHasError = error,
                        isLoading = false
                    )
                }
            }
        )
    }

    private val eventChannel = Channel<Pair<TimerTypeId, TimerEvent>>(Channel.UNLIMITED)

    // call this variable for starting or stopping the countdown timer
    @OptIn(DelicateCoroutinesApi::class)
    fun dispatch(timerId: TimerTypeId, event: TimerEvent) {
        if (!eventChannel.isClosedForSend) {
            viewModelScope.launch {
                eventChannel.trySend(timerId to event)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun startTimers() {
        viewModelScope.launch {
            eventChannel.consumeAsFlow().flatMapLatest { (timerId, event) ->
                when (event) {
                    TimerEvent.STARTED -> {
                        val initialTime =
                            viewState.value.timerState?.get(timerId)?.remainingTime ?: 0L
                        eventChannel.consumeAsFlow().startCountUp(timerId, initialTime, 20000)
                    }

                    TimerEvent.COUNTING -> {
                        val remainingTime =
                            viewState.value.timerState?.get(timerId)?.remainingTime ?: 0L
                        flowOf(timerId to remainingTime)
                    }

                    TimerEvent.FINISHED -> {
                        cameraManager.stopRecording()
                        emptyFlow()
                    }
                }
            }.onEach { (timerId, remainingTime) ->
                Log.i("second", "on each second is $remainingTime")
                updateState(
                    timerId,
                    remainingTime,
                    timerStatus = if (remainingTime < 20) TimerStatus.IS_RUNNING else TimerStatus.IS_FINISHED,
                )
                if (remainingTime >= 20) finishTimer()
            }.launchIn(viewModelScope)
        }
    }

    private fun finishTimer() {
        dispatch(TimerTypeId.VIDEO_TAKEN_TIMER, TimerEvent.FINISHED)
    }

    private fun updateState(
        timerId: TimerTypeId,
        remainingTime: Long,
        timerStatus: TimerStatus = TimerStatus.IS_LOADING
    ) {
        setState {
            it.copy(
                timerState = it.timerState?.toMutableMap().apply {
                    this?.set(timerId, TimerState(remainingTime, timerStatus))
                }
            )
        }
    }

    private fun videoCaptured() {
        setState { state ->
            state.copy(
                isCapturingVideo = true,
            )
        }
        val videoFile = getFile()
        setState {
            it.copy(
                timerState = mapOf(
                    (TimerTypeId.VIDEO_TAKEN_TIMER to TimerState(remainingTime = 0L))
                )
            )
        }
        startTimers()
        dispatch(TimerTypeId.VIDEO_TAKEN_TIMER, TimerEvent.STARTED)
        cameraManager.startRecording(videoFile, onCaptured = {
            viewModelScope.launch {

                videoCompressor.compress(
                    videoFile.absolutePath,
                    configureWith = Configuration(
                        quality = VideoQuality.LOW,
                        videoNames = videoFile.name,
                        isMinBitrateCheckEnabled = false,
                        keepOriginalResolution = true,
                    ),
                    listener = object : CompressionListener {
                        override fun onProgress(percent: Float) {
                        }

                        override fun onStart() {
                            setState { state ->
                                state.copy(
                                    isLoading = true,
                                    isCompressing = true
                                )
                            }

                        }

                        override fun onSuccess(size: Long, path: String?) {
                            setState { state ->
                                state.copy(
                                    isLoading = false,
                                    isCapturingVideo = false,
                                    videoCaptured = true,
                                    isCompressing = false,
                                    savedFileUri = path,
                                    cameraHasError = null
                                )
                            }
                        }

                        override fun onFailure(failureMessage: String) {
                            Log.wtf("failureMessage", failureMessage)
                        }

                        override fun onCancelled() {
                            Log.wtf("TAG", "compression has been cancelled")
                        }
                    },
                )
            }
        }, onError = { error ->
            setState { state ->
                state.copy(
                    videoCaptured = false,
                    cameraHasError = error
                )
            }
        })

    }

    private fun clearVideo() {
        viewModelScope.launch {
            setState {
                it.copy(
                    isLoading = true,
                    isCapturingVideo = true
                )
            }
            viewState.value.savedFileUri?.let {
                val deleteFile = fileManager.deleteFile(it)
                if (deleteFile) {
                    delay(500)
                    setState { state ->
                        state.copy(
                            isLoading = false,
                            isCapturingVideo = false,
                            videoCaptured = false,
                            isCompressing = false,
                            savedFileUri = null,
                            cameraHasError = null
                        )
                    }
                } else {
                    setState {
                        it.copy(
                            isLoading = false,
                            isCapturingVideo = false
                        )
                    }
                }
            } ?: run {
                setState {
                    it.copy(
                        isLoading = false,
                        isCapturingVideo = false
                    )
                }
            }
        }
    }

    private fun videoCapturing() {

    }

    private fun getFile() = fileManager.createVideoFile()
    private fun toggleCamera() {
        viewModelScope.launch {
            cameraManager.toggleCamera()
            setState { state ->
                state.copy(
                    isFrontCamera = cameraManager.isFrontCamera(),
                    cameraHasError = null
                )
            }
        }
    }
}
