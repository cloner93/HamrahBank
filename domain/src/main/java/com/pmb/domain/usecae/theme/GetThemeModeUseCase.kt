package com.pmb.domain.usecae.theme

import com.pmb.core.platform.ThemeMode
import com.pmb.domain.repository.theme.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeModeUseCase @Inject constructor(private val repo: ThemeRepository) {
    operator fun invoke(): Flow<ThemeMode> = repo.getThemeModeFlow()
}