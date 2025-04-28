package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.OtpEntity
import com.pmb.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResendOtpCodeUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<ResendOtpCodeUseCase.Param, OtpEntity>() {
    override suspend fun execute(params: Param): Flow<Result<OtpEntity>> =
        profileRepository.resendOtpCode(
            id = params.id,
            phoneNumber = params.phoneNumber
        )


    data class Param(val id: Long, val phoneNumber: String)
}