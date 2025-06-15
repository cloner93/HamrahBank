package com.pmb.domain.usecae.theme

import com.pmb.core.platform.ThemeMode
import com.pmb.domain.repository.theme.ThemeRepository
import javax.inject.Inject

class SaveThemeModeUseCase @Inject constructor(private val repo: ThemeRepository) {
    suspend operator fun invoke(mode: ThemeMode) = repo.saveThemeMode(mode)
}
