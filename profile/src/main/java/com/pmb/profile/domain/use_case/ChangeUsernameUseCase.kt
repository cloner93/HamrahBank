package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.PersonalInfoEntity
import com.pmb.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangeUsernameUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<ChangeUsernameUseCase.Param, PersonalInfoEntity>() {
    override suspend fun execute(params: Param): Flow<Result<PersonalInfoEntity>> =
        profileRepository.changeUsername(params.userId, params.username)


    data class Param(val userId: Long, val username: String)
}