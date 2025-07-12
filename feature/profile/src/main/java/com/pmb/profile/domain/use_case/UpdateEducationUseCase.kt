package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.EducationEntity
import com.pmb.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateEducationUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<UpdateEducationUseCase.Param, EducationEntity>() {
    override suspend fun execute(params: Param): Flow<Result<EducationEntity>> =
        profileRepository.updateEducation(params.id, params.job)

    data class Param(val id: Long, val job: String)
}