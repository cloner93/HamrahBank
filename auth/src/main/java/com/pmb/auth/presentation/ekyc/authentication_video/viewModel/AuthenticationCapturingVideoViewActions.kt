package com.pmb.auth.presentation.ekyc.authentication_video.viewModel

import com.pmb.camera.platform.PhotoViewActions
import com.pmb.camera.platform.VideoViewActions

sealed interface AuthenticationCapturingVideoViewActions : VideoViewActions {
    data object ClearAlert : AuthenticationCapturingVideoViewActions
    data object FinishTimer : AuthenticationCapturingVideoViewActions
    data object ClearVideo : AuthenticationCapturingVideoViewActions
    data class SendVideo(val uri: String) : AuthenticationCapturingVideoViewActions
}