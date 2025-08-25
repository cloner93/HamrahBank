package com.pmb.data.serviceProvider.local.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pmb.data.SecurityManager
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.UserData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
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

    private val mainDepositKey = stringPreferencesKey("mainDepositKey")
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
        val jsonString = withContext(Dispatchers.IO) {
            URLDecoder.decode(decryptedData, "UTF-8")
        }
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

    override suspend fun setDepositAsMainDeposit(deposit: DepositModel): Boolean {
        return try{
            context.dataStore.edit { prefs ->
                val json = Json { ignoreUnknownKeys = true }
                val mainDepositString = json.encodeToString(deposit)
                val e = URLEncoder.encode(mainDepositString, "UTF-8")
                prefs[mainDepositKey] = e
            }
            true
        }catch (e:Exception){
            false
        }
    }

    override suspend fun getMainDeposit(): DepositModel? {
         try {
            val preferences = context.dataStore.data.first()
            val mainDepositString = preferences[mainDepositKey]
            mainDepositString?.let {
                val json = Json { ignoreUnknownKeys = true }
                val jsonString = URLDecoder.decode(mainDepositString, "UTF-8")
                val deposit = json.decodeFromString<DepositModel>(jsonString)
                return deposit
            }?.run {
                return null
            }
        } catch (e: Exception) {
            return null
        }
        return null
    }
}