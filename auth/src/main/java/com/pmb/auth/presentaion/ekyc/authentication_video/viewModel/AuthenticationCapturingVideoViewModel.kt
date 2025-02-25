package com.pmb.auth.presentaion.ekyc.authentication_video.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.ekyc.capture_video.entity.CapturingVideoParams
import com.pmb.auth.domain.ekyc.capture_video.useCase.SendVideoUseCase
import com.pmb.camera.platform.VideoCameraManagerImpl
import com.pmb.camera.platform.VideoViewActions
import com.pmb.compressor.`interface`.CompressionListener
import com.pmb.compressor.compression.VideoQuality
import com.pmb.compressor.compression.VideoCompressor
import com.pmb.compressor.config.Configuration
import com.pmb.compressor.config.VideoResizer
import com.pmb.core.fileManager.FileManager
import com.pmb.core.permissions.PermissionDispatcher
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationCapturingVideoViewModel @Inject constructor(
    initialState: AuthenticationCapturingVideoViewState,
    private val permissionDispatcher: PermissionDispatcher,
    private val cameraManager: VideoCameraManagerImpl,
    private val videoCompressor: VideoCompressor,
    private val fileManager: FileManager,
    private val sendVideoUseCase: SendVideoUseCase
) : BaseViewModel<
        VideoViewActions,
        AuthenticationCapturingVideoViewState,
        AuthenticationCapturingVideoViewEvents>(initialState) {
    override fun handle(action: VideoViewActions) {
        when (action) {
            is VideoViewActions.RequestCameraPermission -> {
                requestCameraPermission(action)
            }

            is VideoViewActions.RequestFilePermission -> requestFilePermission(action)
            is VideoViewActions.VideoCaptured -> videoCaptured()
            is VideoViewActions.CapturingVideo -> videoCapturing()
            is VideoViewActions.ToggleCamera -> toggleCamera()
            is VideoViewActions.PreviewCamera -> {
                previewCamera(action)
            }

            is AuthenticationCapturingVideoViewActions.SendVideo -> {
                handleSendFacePhoto(action)
            }

            is AuthenticationCapturingVideoViewActions.ClearAlert -> {
                setState {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    private fun handleSendFacePhoto(action: AuthenticationCapturingVideoViewActions.SendVideo) {
        viewModelScope.launch {
            sendVideoUseCase.invoke(CapturingVideoParams(action.uri)).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false
                            )
                        }
                        postEvent(AuthenticationCapturingVideoViewEvents.VideoCaptured)
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                alertModelState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onDismissed = {
                                        setState {
                                            it.copy(alertModelState = null)
                                        }
                                    },
                                    onActionPerformed = {
                                        setState {
                                            it.copy(alertModelState = null)
                                        }
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

            permissionDispatcher.initialize(action.managedActivityResultLauncher)
            permissionDispatcher.requestSinglePermission(
                permission = android.Manifest.permission.CAMERA,
                onPermissionGranted = {
                    setState { state ->
                        Log.i("per", "You have permission for using camera")
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

    private fun requestFilePermission(action: VideoViewActions.RequestFilePermission) {
        viewModelScope.launch {
            permissionDispatcher.initialize(multiplePermissionLauncher = action.managedActivityResultLauncher)
            permissionDispatcher.requestMultiplePermission(
                permissions = arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
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

    private fun videoCaptured() {
        val videoFile = getFile()

        cameraManager.startRecording(videoFile, onCaptured = {
            viewModelScope.launch {

                videoCompressor.compress(
                    videoFile.absolutePath,
                    configureWith = Configuration(
                        quality = VideoQuality.LOW,
                        videoNames = videoFile.name,
                        isMinBitrateCheckEnabled = false,
                        resizer = VideoResizer.limitSize(1280.0)
                    ),
                    listener = object : CompressionListener {
                        override fun onProgress(percent: Float) {
                            //Update UI

                        }

                        override fun onStart() {


                        }

                        override fun onSuccess(size: Long, path: String?) {
                            setState { state ->
                                state.copy(
                                    isLoading = false,
                                    isCapturingVideo = false,
                                    videoCaptured = true,
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
                            // make UI changes, cleanup, etc
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