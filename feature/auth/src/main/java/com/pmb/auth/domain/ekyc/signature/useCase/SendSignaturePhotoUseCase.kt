package com.pmb.auth.domain.ekyc.signature.useCase

import com.pmb.auth.domain.ekyc.signature.entity.SignatureEntity
import com.pmb.auth.domain.ekyc.signature.entity.SignatureParams
import com.pmb.auth.domain.ekyc.signature.repository.SignatureRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendSignaturePhotoUseCase @Inject constructor(
    private val signatureRepository: SignatureRepository
) : BaseUseCase<SignatureParams, SignatureEntity>() {
    override suspend fun execute(params: SignatureParams): Flow<Result<SignatureEntity>> =
        signatureRepository.sendSignaturePhoto(params)
}