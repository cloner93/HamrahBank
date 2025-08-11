package com.pmb.auth.presentation.register.register_face_photo.viewModel

import com.pmb.camera.platform.PhotoViewActions

sealed interface RegisterFacePhotoCapturedViewActions : PhotoViewActions {
    data object ClearAlert : RegisterFacePhotoCapturedViewActions
    data object SendFacePhoto :RegisterFacePhotoCapturedViewActions
}