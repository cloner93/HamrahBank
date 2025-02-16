package com.pmb.auth.presentaion.ekyc.authenticationVideo.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface AuthenticationCapturingVideoViewEvents : BaseViewEvent {
    data object SignaturePhotoCaptured : AuthenticationCapturingVideoViewEvents
}