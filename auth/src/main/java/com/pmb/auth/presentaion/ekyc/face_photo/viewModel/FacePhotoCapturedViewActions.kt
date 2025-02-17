package com.pmb.auth.presentaion.ekyc.face_photo.viewModel

import com.pmb.camera.platform.PhotoViewActions

sealed interface FacePhotoCapturedViewActions : PhotoViewActions {
    data object ClearAlert : FacePhotoCapturedViewActions
    data class SendFacePhoto(val uri: String) :FacePhotoCapturedViewActions
}