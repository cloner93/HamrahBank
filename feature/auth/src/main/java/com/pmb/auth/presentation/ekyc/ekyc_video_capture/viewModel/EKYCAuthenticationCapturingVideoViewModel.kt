package com.pmb.auth.presentation.ekyc.ekyc_video_capture.viewModel

import android.Manifest
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerEvent
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerState
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerStatus
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerTypeId
import com.pmb.auth.presentation.register.register_video.viewModel.RegisterCapturingVideoViewActions
import com.pmb.auth.presentation.register.register_video.viewModel.RegisterCapturingVideoViewEvents
import com.pmb.auth.utils.startCountUp
import com.pmb.camera.platform.VideoCameraManagerImpl
import com.pmb.camera.platform.VideoViewActions
import com.pmb.compressor.compression.VideoCompressor
import com.pmb.compressor.compression.VideoQuality
import com.pmb.compressor.config.Configuration
import com.pmb.core.fileManager.FileManager
import com.pmb.core.permissions.PermissionDispatcher
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.core.utils.Base64FileHelper
import com.pmb.domain.usecae.auth.newPassword.NewPasswordFetchAdmittanceTextUseCase
import com.pmb.domain.usecae.auth.newPassword.NewPasswordWithEKYCParams
import com.pmb.domain.usecae.auth.newPassword.NewPasswordWithEKYCUseCase
import com.pmb.domain.usecae.auth.newPassword.NewPasswordWithVerifyParams
import com.pmb.domain.usecae.auth.newPassword.NewPasswordWithVerifyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
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
    private val newPasswordFetchAdmittanceTextUseCase: NewPasswordFetchAdmittanceTextUseCase,
    private val newPasswordWithEKYCUseCase: NewPasswordWithEKYCUseCase,
    private val newPasswordWithVerifyUseCase: NewPasswordWithVerifyUseCase
) : BaseViewModel<
        VideoViewActions,
        EKYCAuthenticationCapturingVideoViewState,
        EKYCAuthenticationCapturingVideoViewEvents>(initialState) {
//    init {
//        handle(EKYCAuthenticationCapturingVideoViewActions.GetAdmittanceText)
//    }
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

            is EKYCAuthenticationCapturingVideoViewActions.GetAdmittanceText -> {
                handleAdmittanceText()
            }

            is EKYCAuthenticationCapturingVideoViewActions.ForgetPasswordVerification -> {
                handleForgetPasswordVerification(action)
            }
        }
    }

    private fun handleForgetPasswordVerification(action: EKYCAuthenticationCapturingVideoViewActions.ForgetPasswordVerification) {
        viewModelScope.launch {
            newPasswordWithVerifyUseCase.invoke(
                NewPasswordWithVerifyParams(
                    nationalCode = action.eKYCSharedViewState.changePasswordNationalId ?: "",
                    password = action.eKYCSharedViewState.changePasswordPassword ?: ""
                )
            ).collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        setState { state ->
                            state.copy(
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

                    is Result.Success -> {
                        setState { state ->
                            state.copy(
                                isLoading = false,
                            )
                        }
                        postEvent(EKYCAuthenticationCapturingVideoViewEvents.ForgetPasswordVerificationSucceed)
                    }

                    is Result.Loading -> {
                        setState { state ->
                            state.copy(
                                isLoading = true,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleAdmittanceText() {
        viewModelScope.launch {
            newPasswordFetchAdmittanceTextUseCase.invoke(Unit).collect { result ->
                when (result) {
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

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                admittanceTextResponse = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleSendFacePhoto(action: EKYCAuthenticationCapturingVideoViewActions.SendVideo) {
        viewModelScope.launch {
            val fileBase64 = viewState.value.videoFileBase64File?.let {
                Base64FileHelper.encodeToBase64(
                    it,
                    viewModelScope
                )
            }
            Log.d("Ali Tag",action.eKYCSharedViewState.toString())
            newPasswordWithEKYCUseCase.invoke(
                NewPasswordWithEKYCParams(
                    nationalCode = action.eKYCSharedViewState.changePasswordNationalId ?: "",
                    authImage = action.eKYCSharedViewState.authImage ?: "",
                    admittanceText = viewState.value.admittanceTextResponse?.admittanceText
                        ?: "",
                    authVideo = fileBase64?.await() ?: "",
                    cardSerialNo = action.eKYCSharedViewState.cardSerialNo ?: "",
                    mobileNumber = action.eKYCSharedViewState.changePasswordPhoneNumber ?: "",
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState { state ->
                            state.copy(
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

                    is Result.Success -> {
                        setState { state ->
                            state.copy(
                                isLoading = false,
                            )
                        }
                        postEvent(EKYCAuthenticationCapturingVideoViewEvents.ForgetPasswordVideoSent)
                    }

                    is Result.Loading -> {
                        setState { state ->
                            state.copy(
                                isLoading = true,
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
                permission = Manifest.permission.CAMERA,
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
                permission = Manifest.permission.RECORD_AUDIO,
                onPermissionGranted = {
                    setState { state ->
                        Log.i("per", "You have permission for using camera")
                        state.copy(hasAudioPermissions = true, hasCameraPermission = true)
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
//        viewModelScope.launch {
//            permissionDispatcher.initialize(multiplePermissionLauncher = action.managedActivityResultLauncher)
//            permissionDispatcher.requestMultiplePermission(
//                permissions = arrayOf(
////                    android.Manifest.permission.RECORD_AUDIO
//                ),
//                onPermissionGranted = {
//                    setState { state ->
//                        state.copy(hasFilePermissions = true)
//                    }
//                },
//                onPermissionDenied = { deniedPermissions ->
//                    setState { state ->
//                        state.copy(cameraHasError = "You don't have ${deniedPermissions} for using file explorer")
//                    }
//                }
//            )
//        }
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
        handleAdmittanceText()
        startTimers()
        dispatch(TimerTypeId.VIDEO_TAKEN_TIMER, TimerEvent.STARTED)
        cameraManager.startRecording(videoFile, onCaptured = {
            viewModelScope.launch {
//                setState { state ->
//                    state.copy(
//                        isLoading = false,
//                        isCapturingVideo = false,
//                        videoCaptured = true,
//                        isCompressing = false,
//                        savedFileUri = videoFile.absolutePath,
//                        cameraHasError = null,
//                    )
//                }
                try {
                    val result = videoCompressor.compress(
                        videoFile.absolutePath,
                        configureWith = Configuration(
                            quality = VideoQuality.LOW,
                            videoNames = videoFile.name,
                            isMinBitrateCheckEnabled = false,
                            keepOriginalResolution = true,
                        ),
//                        override fun onSuccess(size: Long, path: String?) {
////                                setState { state ->
////                                    state.copy(
////                                        isLoading = false,
////                                        isCapturingVideo = false,
////                                        videoCaptured = true,
////                                        isCompressing = false,
////                                        savedFileUri = path,
////                                        cameraHasError = null,
////                                    )
////                            }
//                        }
//
//                        override fun onFailure(failureMessage: String) {
//                            Log.wtf("failureMessage", failureMessage)
//                        }
//
//                        override fun onCancelled() {
//                            Log.wtf("TAG", "compression has been cancelled")
//                        }
//                    },
                    )
                    setState { state ->
                        state.copy(
                            isLoading = false,
                            isCapturingVideo = false,
                            videoCaptured = true,
                            isCompressing = false,
                            savedFileUri = result.resultPath,
                            videoFileBase64File = result.file,
                            cameraHasError = null,

                            )
                    }
                } catch (e: Exception) {

                }
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
                    handleAdmittanceText()
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
