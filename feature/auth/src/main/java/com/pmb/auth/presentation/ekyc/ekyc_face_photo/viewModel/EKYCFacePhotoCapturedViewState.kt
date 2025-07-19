package com.pmb.auth.presentation.ekyc.ekyc_face_photo.viewModel


import com.pmb.camera.platform.PhotoViewState
import com.pmb.core.platform.AlertModelState

data class EKYCFacePhotoCapturedViewState(
    val isLoading :Boolean = false,
    val alertModelState: AlertModelState?=null,
    override val hasCameraPermission: Boolean = false,
    override val hasFilePermissions: Boolean = false,
    override val isCameraReady: Boolean = false,
    override val isFrontCamera: Boolean = false,
    override val isCapturingPhoto: Boolean = false,
    override val photoCaptured: Boolean = false,
    override val savedFileUri: String? = null,
    override val cameraHasError: String? = null,
    override val isCameraLoading: Boolean = false
) : PhotoViewState
