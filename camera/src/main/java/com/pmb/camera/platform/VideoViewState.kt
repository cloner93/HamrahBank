package com.pmb.camera.platform

import com.pmb.core.platform.BaseViewState

interface VideoViewState:BaseViewState {
    val hasCameraPermission: Boolean
    val hasFilePermissions: Boolean
    val hasAudioPermissions: Boolean
    val isCameraReady: Boolean
    val isFrontCamera: Boolean
    val isCapturingVideo: Boolean
    val videoCaptured: Boolean
    val savedFileUri: String?
    val cameraHasError: String?
    val isCameraLoading: Boolean
    val isCompressing:Boolean
}