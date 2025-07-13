package com.pmb.auth.presentation.scan_card_info.scan_card.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.ekyc.face_photo.entity.FacePhotoParams
import com.pmb.auth.domain.ekyc.face_photo.useCase.SendFacePhotoUseCase
import com.pmb.camera.platform.CameraManagerImpl
import com.pmb.camera.platform.PhotoViewActions
import com.pmb.core.permissions.PermissionDispatcher
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanCardViewModel @Inject constructor(
    initialState: ScanCardViewState,
    private val permissionDispatcher: PermissionDispatcher,
    private val cameraManager: CameraManagerImpl,
    private val sendFacePhotoUseCase: SendFacePhotoUseCase
) : BaseViewModel<PhotoViewActions, ScanCardViewState, ScanCardViewEvents>(
    initialState
) {
    override fun handle(action: PhotoViewActions) {
        when (action) {
            is PhotoViewActions.RequestCameraPermission -> {
                requestCameraPermission(action)
            }

            is PhotoViewActions.RequestFilePermission -> requestFilePermission(action)
            is PhotoViewActions.CapturePhoto -> {

            }

            is PhotoViewActions.ToggleCamera -> {

            }

            is PhotoViewActions.PreviewCamera -> {
                previewCamera(action)
            }

            is PhotoViewActions.ClearPhoto -> {
            }

            is ScanCardViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is ScanCardViewActions.SendScannedCard -> {
                handleSendScanCard(action)
            }
        }
    }

    private fun handleSendScanCard(action: ScanCardViewActions.SendScannedCard) {
        viewModelScope.launch {
            sendFacePhotoUseCase.invoke(FacePhotoParams(action.uri)).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                alertModelState = null,
                                hasCameraPermission = false,
                                hasFilePermissions = false,
                                isCameraReady = false,
                                isFrontCamera = false,
                                isCapturingPhoto = false,
                                photoCaptured = false,
                                savedFileUri = null,
                                cameraHasError = null,
                                isCameraLoading = false
                            )
                        }
                        postEvent(ScanCardViewEvents.SendScanCard)
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
}