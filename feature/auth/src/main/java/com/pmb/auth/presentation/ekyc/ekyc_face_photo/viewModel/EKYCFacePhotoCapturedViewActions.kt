package com.pmb.auth.presentation.ekyc.ekyc_face_photo.viewModel

import com.pmb.camera.platform.PhotoViewActions

sealed interface EKYCFacePhotoCapturedViewActions : PhotoViewActions {
    data object ClearAlert : EKYCFacePhotoCapturedViewActions
    data object SendFacePhoto :EKYCFacePhotoCapturedViewActions
}
