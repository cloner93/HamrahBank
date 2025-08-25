package com.pmb.auth.presentation.register.register_video.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface RegisterCapturingVideoViewEvents : BaseViewEvent {
    data object VideoCaptured : RegisterCapturingVideoViewEvents
    data object VideoSent : RegisterCapturingVideoViewEvents
}