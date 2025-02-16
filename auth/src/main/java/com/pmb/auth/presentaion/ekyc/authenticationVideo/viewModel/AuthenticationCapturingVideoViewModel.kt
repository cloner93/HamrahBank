package com.pmb.auth.presentaion.ekyc.authenticationVideo.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.ekyc.captureVideo.entity.CapturingVideoParams
import com.pmb.auth.domain.ekyc.captureVideo.repository.CapturingVideoRepository
import com.pmb.auth.domain.ekyc.captureVideo.useCase.SendVideoUseCase
import com.pmb.auth.domain.ekyc.facePhoto.entity.FacePhotoParams
import com.pmb.auth.domain.ekyc.facePhoto.useCase.SendFacePhotoUseCase
import com.pmb.camera.platform.CameraManager
import com.pmb.camera.platform.VideoViewActions
import com.pmb.core.compression.VideoCompressor
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
    private val cameraManager: CameraManager,
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
//        _state.value = _state.value.copy(isLoading = true)
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

    }

    private fun videoCapturing() {

    }

    private fun getFile() = fileManager.createImageFile()
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