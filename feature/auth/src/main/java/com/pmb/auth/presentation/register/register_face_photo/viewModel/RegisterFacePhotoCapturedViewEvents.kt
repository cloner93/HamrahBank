package com.pmb.auth.presentation.register.register_face_photo.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface RegisterFacePhotoCapturedViewEvents : BaseViewEvent {
    data object FacePhotoCaptured : RegisterFacePhotoCapturedViewEvents
}