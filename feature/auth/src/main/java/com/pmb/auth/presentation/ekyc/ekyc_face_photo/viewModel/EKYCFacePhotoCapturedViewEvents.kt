package com.pmb.auth.presentation.ekyc.ekyc_face_photo.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface EKYCFacePhotoCapturedViewEvents : BaseViewEvent {
    data object FacePhotoCaptured : EKYCFacePhotoCapturedViewEvents
}
