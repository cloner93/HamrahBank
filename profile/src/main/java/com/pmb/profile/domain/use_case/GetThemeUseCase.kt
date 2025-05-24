package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.core.platform.ThemeMode
import com.pmb.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseUseCase<Unit, ThemeMode>() {
    override suspend fun execute(params: Unit): Flow<Result<ThemeMode>> =
        profileRepository.fetchThemeMode()
}