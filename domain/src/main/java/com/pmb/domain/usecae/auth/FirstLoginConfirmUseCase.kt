package com.pmb.domain.usecae.auth

import com.pmb.core.platform.BaseUseCase
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.SendOtpResponse
import com.pmb.domain.repository.auth.FirstLoginConfirmRepository
import javax.inject.Inject

class FirstLoginConfirmUseCase @Inject constructor(
    private val firstLoginConfirmRepository: FirstLoginConfirmRepository
) : BaseUseCase<SendOtpRequest, SendOtpResponse>() {
    override suspend fun execute(params: SendOtpRequest) =
        firstLoginConfirmRepository.sendOtp(sendOtpRequest = params)
}