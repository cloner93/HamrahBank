package com.pmb.data.serviceProvider.local.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pmb.data.SecurityManager
import com.pmb.domain.model.UserData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

@Singleton
class UserDataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val securityManager: SecurityManager
) : UserDataStore {

    private val encryptedJson = stringPreferencesKey("encryptedJson")
    private val encryptedIv = stringPreferencesKey("encryptedIv")

    override suspend fun setUserData(userData: UserData) {
        context.dataStore.edit { preferences ->

            val json = Json { ignoreUnknownKeys = true }
            val transactionJson = json.encodeToString(userData)
            val userDataJson = URLEncoder.encode(transactionJson, "UTF-8")

            val encrypted = securityManager.encrypt(userDataJson)

            preferences[encryptedJson] = encrypted.ciphertext
            preferences[encryptedIv] = encrypted.iv
        }
    }

    override suspend fun getUserData(): UserData {
        val preferences = context.dataStore.data.first()

        val encryptedData = preferences[encryptedJson] ?: ""
        val iv = preferences[encryptedIv] ?: ""

        val encrypted = SecurityManager.EncryptedData(encryptedData, iv)
        val decryptedData = securityManager.decrypt(encrypted)

        val jsonO = Json { ignoreUnknownKeys = true }
        val jsonString = URLDecoder.decode(decryptedData, "UTF-8")
        val userData = jsonO.decodeFromString<UserData>(jsonString)

        return userData
    }

    override suspend fun logoutUser(): Boolean {
        return try {
            context.dataStore.edit { preferences ->
                preferences.remove(encryptedJson)
                preferences.remove(encryptedIv)
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}