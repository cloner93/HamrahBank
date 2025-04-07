package com.pmb.auth.domain.ekyc.face_photo.useCase

import com.pmb.auth.domain.ekyc.face_photo.entity.FacePhotoEntity
import com.pmb.auth.domain.ekyc.face_photo.entity.FacePhotoParams
import com.pmb.auth.domain.ekyc.face_photo.repository.FacePhotoRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendFacePhotoUseCase @Inject constructor(
    private val facePhotoRepository: FacePhotoRepository
) : BaseUseCase<FacePhotoParams, FacePhotoEntity>() {
    override suspend fun execute(params: FacePhotoParams): Flow<Result<FacePhotoEntity>> =
        facePhotoRepository.sendPhotoOfCustomerFace(params)
}