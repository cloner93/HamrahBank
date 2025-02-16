package com.pmb.auth.presentaion.ekyc.authenticationVideo.viewModel

import com.pmb.camera.platform.VideoViewState
import com.pmb.core.platform.AlertModelState

data class AuthenticationCapturingVideoViewState(
    val isLoading :Boolean = false,
    val alertModelState: AlertModelState?=null,
    override val hasCameraPermission: Boolean = false,
    override val hasFilePermissions: Boolean = false,
    override val isCameraReady: Boolean = false,
    override val isFrontCamera: Boolean = false,
    override val isCapturingVideo: Boolean = false,
    override val videoCaptured: Boolean = false,
    override val savedFileUri: String? = null,
    override val cameraHasError: String? = null,
    override val isCameraLoading: Boolean = false
) : VideoViewState