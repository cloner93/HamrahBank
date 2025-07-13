package com.pmb.data.repository.auth

import com.pmb.core.platform.Result
import com.pmb.data.appManager.AppManager
import com.pmb.data.mapper.authService.toDomain
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.SendOtpResponse
import com.pmb.domain.repository.auth.FirstLoginConfirmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirstLoginConfirmRepositoryImpl @Inject constructor(
    private val appManager: AppManager
) : FirstLoginConfirmRepository {
    override suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SendOtpResponse>> {
        return appManager.getAuthService().sendOtp(sendOtpRequest).mapApiResult {
            it.second.toDomain()
        }
    }
}
