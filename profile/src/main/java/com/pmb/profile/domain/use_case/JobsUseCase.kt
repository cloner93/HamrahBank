package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.JobEntity
import com.pmb.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JobsUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<Unit, List<JobEntity>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<JobEntity>>> =
        profileRepository.fetchJobs()
}