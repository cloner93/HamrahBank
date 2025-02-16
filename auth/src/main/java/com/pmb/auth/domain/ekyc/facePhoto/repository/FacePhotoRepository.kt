package com.pmb.auth.domain.ekyc.facePhoto.repository

import com.pmb.auth.domain.ekyc.facePhoto.entity.FacePhotoEntity
import com.pmb.auth.domain.ekyc.facePhoto.entity.FacePhotoParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface FacePhotoRepository {
    fun sendPhotoOfCustomerFace(params: FacePhotoParams): Flow<Result<FacePhotoEntity>>
}