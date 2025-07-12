package com.pmb.auth.domain.ekyc.face_photo.repository

import com.pmb.auth.domain.ekyc.face_photo.entity.FacePhotoEntity
import com.pmb.auth.domain.ekyc.face_photo.entity.FacePhotoParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface FacePhotoRepository {
    fun sendPhotoOfCustomerFace(params: FacePhotoParams): Flow<Result<FacePhotoEntity>>
}