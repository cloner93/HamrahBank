package com.pmb.auth.presentaion.ekyc.authentication_video.viewModel

import com.pmb.camera.platform.VideoViewActions

sealed interface AuthenticationCapturingVideoViewActions : VideoViewActions {
    data object ClearAlert : AuthenticationCapturingVideoViewActions
    data class SendVideo(val uri: String) : AuthenticationCapturingVideoViewActions
}