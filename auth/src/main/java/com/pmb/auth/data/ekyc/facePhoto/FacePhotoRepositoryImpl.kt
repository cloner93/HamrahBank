package com.pmb.auth.data.ekyc.facePhoto

import com.pmb.auth.domain.ekyc.facePhoto.entity.FacePhotoEntity
import com.pmb.auth.domain.ekyc.facePhoto.entity.FacePhotoParams
import com.pmb.auth.domain.ekyc.facePhoto.repository.FacePhotoRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FacePhotoRepositoryImpl @Inject constructor() : FacePhotoRepository {
    override fun sendPhotoOfCustomerFace(params: FacePhotoParams): Flow<Result<FacePhotoEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            params.uri.takeIf { !it.isNullOrEmpty() }?.let {
                emit(Result.Success(FacePhotoEntity(isSuccess = true)))
            } ?: run {
                emit(Result.Error(message = "Your photo's address is not valid"))
            }
        }
}