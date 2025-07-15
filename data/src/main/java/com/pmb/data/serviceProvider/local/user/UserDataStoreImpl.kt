package com.pmb.data.serviceProvider.local.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pmb.domain.model.UserData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

@Singleton
class UserDataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserDataStore {

    private val customerUserKey = stringPreferencesKey("customer_id")
    private val usernameKey = stringPreferencesKey("username")
    private val passwordKey = stringPreferencesKey("password")

    override suspend fun setUserData(userData: UserData) {
        context.dataStore.edit { preferences ->
            preferences[customerUserKey] = userData.customerId
            preferences[usernameKey] = userData.username
            preferences[passwordKey] = userData.password
        }
    }

    override suspend fun getUserData(): UserData? {
        val preferences = context.dataStore.data.first()
        val customerId = preferences[customerUserKey]
        val username = preferences[usernameKey]
        val password = preferences[passwordKey]

        return if (customerId != null && username != null && password != null) {
            UserData(customerId, username, password)
        } else {
            null
        }
    }
}