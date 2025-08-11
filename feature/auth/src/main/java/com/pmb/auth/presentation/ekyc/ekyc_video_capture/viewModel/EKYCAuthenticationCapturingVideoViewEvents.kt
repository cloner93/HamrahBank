package com.pmb.auth.presentation.ekyc.ekyc_video_capture.viewModel

import com.pmb.auth.presentation.register.register_video.viewModel.RegisterCapturingVideoViewEvents
import com.pmb.core.platform.BaseViewEvent

sealed interface EKYCAuthenticationCapturingVideoViewEvents : BaseViewEvent {
    data object ForgetPasswordVerificationSucceed : EKYCAuthenticationCapturingVideoViewEvents
    data object ForgetPasswordVideoSent : EKYCAuthenticationCapturingVideoViewEvents
}
