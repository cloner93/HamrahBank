package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.PersonalInfoEntity
import com.pmb.profile.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonalInfoUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<PersonalInfoUseCase.Param, PersonalInfoEntity>() {

    override suspend fun execute(params: Param): Flow<Result<PersonalInfoEntity>> =
        profileRepository.fetchPersonalInfo(params)

    data class Param(val userId: Long)
}