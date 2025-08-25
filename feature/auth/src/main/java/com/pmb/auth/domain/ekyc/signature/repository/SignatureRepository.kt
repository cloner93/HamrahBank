package com.pmb.auth.domain.ekyc.signature.repository

import com.pmb.auth.domain.ekyc.signature.entity.SignatureEntity
import com.pmb.auth.domain.ekyc.signature.entity.SignatureParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface SignatureRepository {
    fun sendSignaturePhoto(signatureParams: SignatureParams): Flow<Result<SignatureEntity>>
}