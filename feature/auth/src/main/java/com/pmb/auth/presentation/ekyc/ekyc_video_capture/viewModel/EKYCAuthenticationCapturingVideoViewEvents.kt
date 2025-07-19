package com.pmb.auth.presentation.ekyc.ekyc_video_capture.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface EKYCAuthenticationCapturingVideoViewEvents : BaseViewEvent {
    data object VideoCaptured : EKYCAuthenticationCapturingVideoViewEvents
}
