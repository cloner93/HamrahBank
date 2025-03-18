package com.pmb.auth.presentation.ekyc.authentication_video.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface AuthenticationCapturingVideoViewEvents : BaseViewEvent {
    data object VideoCaptured : AuthenticationCapturingVideoViewEvents
}