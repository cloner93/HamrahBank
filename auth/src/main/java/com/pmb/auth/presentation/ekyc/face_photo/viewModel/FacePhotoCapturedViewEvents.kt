package com.pmb.auth.presentation.ekyc.face_photo.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface FacePhotoCapturedViewEvents : BaseViewEvent {
    data object FacePhotoCaptured : FacePhotoCapturedViewEvents
}