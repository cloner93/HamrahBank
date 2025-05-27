package com.pmb.domain.theme.repository

import com.pmb.core.platform.ThemeMode
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    suspend fun saveThemeMode(mode: ThemeMode)
    fun getThemeModeFlow(): Flow<ThemeMode>
}