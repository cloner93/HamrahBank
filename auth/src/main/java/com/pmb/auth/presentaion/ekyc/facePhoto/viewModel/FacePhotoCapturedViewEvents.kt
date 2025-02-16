package com.pmb.auth.presentaion.ekyc.facePhoto.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface FacePhotoCapturedViewEvents : BaseViewEvent {
    data object FacePhotoCaptured : FacePhotoCapturedViewEvents
}