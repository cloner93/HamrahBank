package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.OtpEntity
import com.pmb.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ChangePhoneNumberUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<ChangePhoneNumberUseCase.Param, OtpEntity>() {
    override suspend fun execute(param: Param): Flow<Result<OtpEntity>> =
        profileRepository.changePhoneNumber(param.userId, param.phoneNumber)


    data class Param(val userId: Long, val phoneNumber: String)
}