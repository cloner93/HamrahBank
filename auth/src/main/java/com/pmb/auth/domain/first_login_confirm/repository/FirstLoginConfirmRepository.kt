package com.pmb.auth.domain.first_login_confirm.repository

import com.pmb.auth.domain.first_login_confirm.entity.SendOtpRequest
import com.pmb.auth.domain.first_login_confirm.entity.SendOtpResponse
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface FirstLoginConfirmRepository {
    suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SendOtpResponse>>
}