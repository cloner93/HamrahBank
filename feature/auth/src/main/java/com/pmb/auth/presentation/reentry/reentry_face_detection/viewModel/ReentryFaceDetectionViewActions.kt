package com.pmb.auth.presentation.reentry.reentry_face_detection.viewModel

import com.pmb.camera.platform.PhotoViewActions

sealed interface ReentryFaceDetectionViewActions : PhotoViewActions {

    data object ClearAlert : ReentryFaceDetectionViewActions
    data class SendFacePhoto(val uri: String) : ReentryFaceDetectionViewActions
}