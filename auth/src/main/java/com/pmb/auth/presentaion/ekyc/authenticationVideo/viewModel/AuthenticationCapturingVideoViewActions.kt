package com.pmb.auth.presentaion.ekyc.authenticationVideo.viewModel

import com.pmb.camera.platform.VideoViewActions

sealed interface AuthenticationCapturingVideoViewActions : VideoViewActions {
    data object ClearAlert : AuthenticationCapturingVideoViewActions
}