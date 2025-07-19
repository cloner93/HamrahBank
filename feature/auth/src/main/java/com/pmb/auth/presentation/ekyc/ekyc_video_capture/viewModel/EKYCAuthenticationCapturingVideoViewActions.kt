package com.pmb.auth.presentation.ekyc.ekyc_video_capture.viewModel

import com.pmb.camera.platform.VideoViewActions

sealed interface EKYCAuthenticationCapturingVideoViewActions : VideoViewActions {
    data object ClearAlert : EKYCAuthenticationCapturingVideoViewActions
    data object FinishTimer : EKYCAuthenticationCapturingVideoViewActions
    data object ClearVideo : EKYCAuthenticationCapturingVideoViewActions
    data class SendVideo(val uri: String) : EKYCAuthenticationCapturingVideoViewActions
}
