package com.pmb.domain.theme.usecae

import com.pmb.core.platform.ThemeMode
import com.pmb.domain.theme.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeModeUseCase @Inject constructor(private val repo: ThemeRepository) {
    operator fun invoke(): Flow<ThemeMode> = repo.getThemeModeFlow()
}