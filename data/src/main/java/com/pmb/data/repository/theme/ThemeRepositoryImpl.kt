package com.pmb.data.repository.theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pmb.core.platform.ThemeMode
import com.pmb.domain.repository.theme.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ThemeRepository {

    private val THEME_KEY = stringPreferencesKey("theme_mode")

    override suspend fun saveThemeMode(mode: ThemeMode) {
        dataStore.edit { prefs ->
            prefs[THEME_KEY] = mode.name
        }
    }

    override fun getThemeModeFlow(): Flow<ThemeMode> {
        return dataStore.data.map { prefs ->
            val theme = prefs[THEME_KEY] ?: ThemeMode.SYSTEM.name
            ThemeMode.valueOf(theme)
        }
    }
}