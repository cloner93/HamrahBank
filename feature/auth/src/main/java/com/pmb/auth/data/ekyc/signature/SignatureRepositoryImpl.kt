package com.pmb.auth.data.ekyc.signature

import com.pmb.auth.domain.ekyc.signature.entity.SignatureEntity
import com.pmb.auth.domain.ekyc.signature.entity.SignatureParams
import com.pmb.auth.domain.ekyc.signature.repository.SignatureRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignatureRepositoryImpl @Inject constructor() : SignatureRepository {
    override fun sendSignaturePhoto(signatureParams: SignatureParams): Flow<Result<SignatureEntity>> =
        flow {
            emit(Result.Loading)
            signatureParams.uri.takeIf { !it.isNullOrEmpty() }?.let {
                emit(Result.Success(SignatureEntity(isSuccess = true)))
            } ?: run {
                emit(Result.Error(message = "Your photo's address is not valid"))
            }
        }
}