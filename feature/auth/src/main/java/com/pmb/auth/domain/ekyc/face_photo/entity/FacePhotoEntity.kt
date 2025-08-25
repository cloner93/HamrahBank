package com.pmb.auth.domain.ekyc.face_photo.entity

data class FacePhotoEntity(
    val isSuccess: Boolean
)

data class FacePhotoParams(
    val uri: String?,
)