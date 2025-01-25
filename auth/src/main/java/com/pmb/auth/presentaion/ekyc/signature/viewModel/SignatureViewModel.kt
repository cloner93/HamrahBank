package com.pmb.auth.presentaion.ekyc.signature.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.camera.platform.CameraManager
import com.pmb.core.compression.ImageCompressor
import com.pmb.core.fileManager.FileManager
import com.pmb.core.permissions.PermissionDispatcher
import com.pmb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignatureViewModel @Inject constructor(
    initialSate: SignatureViewState,
    private val permissionDispatcher: PermissionDispatcher,
    private val cameraManager: CameraManager,
    private val imageCompressor: ImageCompressor,
    private val fileManager: FileManager
) : BaseViewModel<SignatureViewActions, SignatureViewState, SignatureViewEvents>(initialSate) {
    override fun handle(action: SignatureViewActions) {
        when (action) {
            is SignatureViewActions.RequestCameraPermission -> requestCameraPermission()
            is SignatureViewActions.RequestFilePermission -> requestFilePermission()
            is SignatureViewActions.TakePhoto -> takePhoto()
            is SignatureViewActions.ToggleCamera -> toggleCamera()
            is SignatureViewActions.PreviewCamera -> {
                previewCamera(action)
            }

            is SignatureViewActions.ClearAlert -> {
                setState {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    private fun requestCameraPermission() {
        viewModelScope.launch {
            permissionDispatcher.requestSinglePermission(
                permission = android.Manifest.permission.CAMERA,
                onPermissionGranted = {
                    setState { state ->
                        state.copy(hasCameraPermission = true)
                    }
                },
                onPermissionDenied = {
                    setState { state ->
                        state.copy(error = "You don't have permission for using camera")
                    }
                }
            )
        }
    }

    private fun requestFilePermission() {
        viewModelScope.launch {
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
                        state.copy(error = "You don't have ${deniedPermissions} for using file explorer")
                    }
                }
            )
        }
    }

    private fun previewCamera(action: SignatureViewActions.PreviewCamera) {
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
                        error = null
                    )
                }
            },
            onError = { error ->
                setState { state ->
                    state.copy(
                        error = error,
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
                    val compressedFile = imageCompressor.compressAndReplaceImage(
                        photoFile.absolutePath,
                        1024,
                        1024,
                        compressionPercentage = 50
                    )

                    setState { state ->
                        state.copy(
                            isCapturingPhoto = false,
                            photoCaptured = compressedFile,
                            savedFileUri = photoFile.absolutePath,
                            error = null
                        )
                    }
                }
            },
            onError = { error ->
                setState { state ->
                    state.copy(
                        isCapturingPhoto = false,
                        error = error
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
                    error = null
                )
            }
        }
    }


}