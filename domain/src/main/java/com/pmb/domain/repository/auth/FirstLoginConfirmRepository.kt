package com.pmb.domain.repository.auth

import com.pmb.core.platform.Result
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.SendOtpResponse
import kotlinx.coroutines.flow.Flow

interface FirstLoginConfirmRepository {
    suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SendOtpResponse>>
}