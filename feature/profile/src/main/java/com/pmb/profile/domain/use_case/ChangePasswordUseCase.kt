package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.PersonalInfoEntity
import com.pmb.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<ChangePasswordUseCase.Param, PersonalInfoEntity>() {
    override suspend fun execute(params: Param): Flow<Result<PersonalInfoEntity>> =
        profileRepository.changePassword(params.userId, params.oldPassword, params.newPassword)

    data class Param(val userId: String, val oldPassword: String, val newPassword: String)
}