package com.pmb.camera.platform

import com.pmb.core.platform.BaseViewState

interface PhotoViewState : BaseViewState {
    val hasCameraPermission: Boolean
    val hasFilePermissions: Boolean
    val isCameraReady: Boolean
    val isFrontCamera: Boolean
    val isCapturingPhoto: Boolean
    val photoCaptured: Boolean
    val savedFileUri: String?
    val cameraHasError: String?
    val isCameraLoading: Boolean

}