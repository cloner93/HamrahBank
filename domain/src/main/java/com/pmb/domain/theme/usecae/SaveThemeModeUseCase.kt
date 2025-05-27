package com.pmb.domain.theme.usecae

import com.pmb.core.platform.ThemeMode
import com.pmb.domain.theme.repository.ThemeRepository
import javax.inject.Inject

class SaveThemeModeUseCase @Inject constructor(private val repo: ThemeRepository) {
    suspend operator fun invoke(mode: ThemeMode) = repo.saveThemeMode(mode)
}
