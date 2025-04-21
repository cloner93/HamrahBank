package com.pmb.profile.domain.profile.useCase

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<Unit, String>() {
    override suspend fun execute(params: Unit): Flow<Result<String>> {
        return profileRepository.logOut()
    }
}