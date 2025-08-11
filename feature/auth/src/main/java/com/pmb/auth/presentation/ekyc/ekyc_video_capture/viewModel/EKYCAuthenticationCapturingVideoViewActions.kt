package com.pmb.auth.presentation.ekyc.ekyc_video_capture.viewModel

import com.pmb.auth.presentation.ekyc.EKYCSharedViewState
import com.pmb.camera.platform.VideoViewActions

sealed interface EKYCAuthenticationCapturingVideoViewActions : VideoViewActions {
    data object ClearAlert : EKYCAuthenticationCapturingVideoViewActions
    data object FinishTimer : EKYCAuthenticationCapturingVideoViewActions
    data object ClearVideo : EKYCAuthenticationCapturingVideoViewActions
    data class SendVideo(val eKYCSharedViewState: EKYCSharedViewState) :
        EKYCAuthenticationCapturingVideoViewActions

    data object GetAdmittanceText : EKYCAuthenticationCapturingVideoViewActions
    data class ForgetPasswordVerification(val eKYCSharedViewState: EKYCSharedViewState) :EKYCAuthenticationCapturingVideoViewActions
}
