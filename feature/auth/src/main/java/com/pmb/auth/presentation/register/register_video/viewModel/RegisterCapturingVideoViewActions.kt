package com.pmb.auth.presentation.register.register_video.viewModel

import com.pmb.camera.platform.VideoViewActions

sealed interface RegisterCapturingVideoViewActions : VideoViewActions {
    data object ClearAlert : RegisterCapturingVideoViewActions
    data object FinishTimer : RegisterCapturingVideoViewActions
    data object ClearVideo : RegisterCapturingVideoViewActions
    data class SendVideo(val uri: String) : RegisterCapturingVideoViewActions
}