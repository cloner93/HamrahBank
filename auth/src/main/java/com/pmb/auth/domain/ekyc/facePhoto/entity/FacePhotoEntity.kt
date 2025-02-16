package com.pmb.auth.domain.ekyc.facePhoto.entity

data class FacePhotoEntity(
    val isSuccess: Boolean
)

data class FacePhotoParams(
    val uri: String?,
)