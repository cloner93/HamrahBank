package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.PersonalInfoEntity
import com.pmb.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubmitOtpCodeUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<SubmitOtpCodeUseCase.Param, PersonalInfoEntity>() {
    override suspend fun execute(params: Param): Flow<Result<PersonalInfoEntity>> =
        profileRepository.submitOtpCode(params.otpId, params.phoneNumber, params.otpCode)


    data class Param(val otpId: Long, val phoneNumber: String, val otpCode: String)
}