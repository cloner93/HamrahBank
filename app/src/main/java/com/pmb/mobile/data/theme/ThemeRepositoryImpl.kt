package com.pmb.mobile.data.theme

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pmb.core.platform.ThemeMode
import com.pmb.mobile.domain.theme.repository.ThemeRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ThemeRepository {
    private val Context.dataStore by preferencesDataStore("settings")
    private val THEME_KEY = stringPreferencesKey("theme_mode")

    override suspend fun saveThemeMode(mode: ThemeMode) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = mode.name
        }
    }

    override fun getThemeModeFlow(): Flow<ThemeMode> {
        return context.dataStore.data.map { prefs ->
            val theme = prefs[THEME_KEY] ?: ThemeMode.SYSTEM.name
            ThemeMode.valueOf(theme)
        }
    }
}