package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.EducationEntity
import com.pmb.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEducationsUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<Unit, List<EducationEntity>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<EducationEntity>>> =
        profileRepository.fetchEducations()
}