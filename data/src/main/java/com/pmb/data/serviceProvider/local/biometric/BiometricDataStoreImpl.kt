package com.pmb.data.serviceProvider.local.biometric

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "biometric")

class BiometricDataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : BiometricDataStore {

    private val biometricState = booleanPreferencesKey("biometric_state")


    override suspend fun getBiometricState(): Boolean {
        val preferences = context.dataStore.data.first()

        return preferences[biometricState] ?: false
    }

    override suspend fun setBiometricState(isEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[biometricState] = isEnabled
        }
    }
}