package com.pmb.auth.presentaion.ekyc.facePhoto.viewModel

import com.pmb.camera.platform.PhotoViewActions

sealed interface FacePhotoCapturedViewActions : PhotoViewActions {
    data object ClearAlert : FacePhotoCapturedViewActions
}