package com.pmb.mobile.domain.theme.usecae

import com.pmb.core.platform.ThemeMode
import com.pmb.mobile.domain.theme.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeModeUseCase @Inject constructor(private val repo: ThemeRepository) {
    operator fun invoke(): Flow<ThemeMode> = repo.getThemeModeFlow()
}