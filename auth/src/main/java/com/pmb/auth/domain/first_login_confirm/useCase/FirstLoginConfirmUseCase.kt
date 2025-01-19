package com.pmb.auth.domain.first_login_confirm.useCase

import com.pmb.auth.domain.first_login_confirm.entity.SendOtpRequest
import com.pmb.auth.domain.first_login_confirm.entity.SendOtpResponse
import com.pmb.auth.domain.first_login_confirm.repository.FirstLoginConfirmRepository
import com.pmb.core.platform.BaseUseCase
import javax.inject.Inject

class FirstLoginConfirmUseCase @Inject constructor(
    private val firstLoginConfirmRepository: FirstLoginConfirmRepository
) : BaseUseCase<SendOtpRequest, SendOtpResponse>() {
    override suspend fun execute(params: SendOtpRequest) =
        firstLoginConfirmRepository.sendOtp(sendOtpRequest = params)
}