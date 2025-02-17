package com.pmb.auth.presentaion.ekyc.facePhoto.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.ekyc.facePhoto.entity.FacePhotoParams
import com.pmb.auth.domain.ekyc.facePhoto.useCase.SendFacePhotoUseCase
import com.pmb.camera.platform.CameraManager
import com.pmb.camera.platform.PhotoViewActions
import com.pmb.core.compression.ImageCompressor
import com.pmb.core.fileManager.FileManager
import com.pmb.core.permissions.PermissionDispatcher
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacePhotoCapturedViewModel @Inject constructor(
    initialState: FacePhotoCapturedViewState,
    private val permissionDispatcher: PermissionDispatcher,
    private val cameraManager: CameraManager,
    private val imageCompressor: ImageCompressor,
    private val fileManager: FileManager,
    private val sendFacePhotoUseCase: SendFacePhotoUseCase
) : BaseViewModel<PhotoViewActions, FacePhotoCapturedViewState, FacePhotoCapturedViewEvents>(
    initialState
) {
    override fun handle(action: PhotoViewActions) {
        when (action) {
            is PhotoViewActions.RequestCameraPermission -> {
                requestCameraPermission(action)
            }

            is PhotoViewActions.RequestFilePermission -> requestFilePermission(action)
            is PhotoViewActions.CapturePhoto -> takePhoto()
            is PhotoViewActions.ToggleCamera -> toggleCamera()
            is PhotoViewActions.PreviewCamera -> {
                previewCamera(action)
            }
            is PhotoViewActions.ClearPhoto -> {
                clearPhoto()
            }

            is FacePhotoCapturedViewActions.SendFacePhoto->{
                handleSendFacePhoto(action)
            }
            is FacePhotoCapturedViewActions.ClearAlert -> {
                setState {
                    it.copy(isLoading = false)
                }
            }
        }
    }
    private fun clearPhoto() {
        viewModelScope.launch {
            setState {
                it.copy(
                    isLoading = true,
                    isCapturingPhoto = true
                )
            }
            viewState.value.savedFileUri?.let {
                val deleteFile = fileManager.deleteFile(it)
                if (deleteFile) {
                    delay(500)
                    setState { state ->
                        state.copy(
                            isLoading = false,
                            photoCaptured = false,
                            savedFileUri = null,
                            cameraHasError = null
                        )
                    }
                } else {
                    setState {
                        it.copy(isLoading = false,
                            isCapturingPhoto = false)
                    }
                }
            } ?: run {
                setState {
                    it.copy(isLoading = false,
                        isCapturingPhoto = false)
                }
            }
        }
    }
    private fun handleSendFacePhoto(action: FacePhotoCapturedViewActions.SendFacePhoto) {
        viewModelScope.launch {
            sendFacePhotoUseCase.invoke(FacePhotoParams(action.uri)).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false
                            )
                        }
                        postEvent(FacePhotoCapturedViewEvents.FacePhotoCaptured)
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

    private fun requestCameraPermission(action: PhotoViewActions.RequestCameraPermission) {
        viewModelScope.launch {

            permissionDispatcher.initialize(action.managedActivityResultLauncher)
            permissionDispatcher.requestSinglePermission(
                permission = android.Manifest.permission.CAMERA,
                onPermissionGranted = {
                    setState { state ->
                        Log.i("per", "You have permission for using camera")
                        state.copy(hasCameraPermission = true, isCapturingPhoto = true)
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

    private fun requestFilePermission(action: PhotoViewActions.RequestFilePermission) {
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
    private fun previewCamera(action: PhotoViewActions.PreviewCamera) {
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
                        cameraHasError = null,
                        isCapturingPhoto = true
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

    private fun getFile() = fileManager.createImageFile()
    private fun takePhoto() {
        val photoFile = getFile()
        setState { state ->
            state.copy(isLoading = true)
        }
        cameraManager.takePhoto(
            photoFile,
            onPhotoCaptured = { isCaptured ->
                viewModelScope.launch {
                    viewModelScope.launch {
                        val compressedFilePath = imageCompressor.compressAndReplaceImage(
                            photoFile.absolutePath, 1024, 1024, compressionPercentage = 50
                        )
                        setState { state ->
                            state.copy(
                                isLoading = false,
                                isCapturingPhoto = false,
                                photoCaptured = compressedFilePath,
                                savedFileUri = photoFile.absolutePath,
                                cameraHasError = null
                            )
                        }
                    }
                }
            },
            onError = { error ->
                setState { state ->
                    state.copy(
                        isCapturingPhoto = false,
                        cameraHasError = error
                    )
                }

            }
        )

    }


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