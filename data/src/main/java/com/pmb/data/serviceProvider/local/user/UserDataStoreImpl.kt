package com.pmb.data.serviceProvider.local.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pmb.domain.model.DepositModel
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
    @ApplicationContext private val context: Context
) : UserDataStore {

    private val customerUserKey = stringPreferencesKey("customer_id")
    private val usernameKey = stringPreferencesKey("username")
    private val firstNameKey = stringPreferencesKey("firstName")
    private val lastNameKey = stringPreferencesKey("lastName")
    private val phoneNumberKey = stringPreferencesKey("phoneNumber")
    private val mainDepositKey = stringPreferencesKey("mainDepositKey")

    override suspend fun setUserData(userData: UserData) {
        context.dataStore.edit { preferences ->
            preferences[customerUserKey] = userData.customerId
            preferences[usernameKey] = userData.username
            preferences[firstNameKey] = userData.firstName
            preferences[lastNameKey] = userData.lastName
            preferences[phoneNumberKey] = userData.phoneNumber
        }
    }

    override suspend fun getUserData(): UserData? {
        val preferences = context.dataStore.data.first()
        val customerId = preferences[customerUserKey]
        val username = preferences[usernameKey]
        val firstName = preferences[firstNameKey]
        val lastName = preferences[lastNameKey]
        val phoneNumber = preferences[phoneNumberKey]

        return if (customerId != null && username != null) {
            UserData(
                customerId = customerId,
                username = username,
                firstName = firstName ?: "",
                lastName = lastName ?: "",
                phoneNumber = phoneNumber ?: ""
            )
        } else {
            null
        }
    }

    override suspend fun logoutUser(): Boolean {
        return try {
            context.dataStore.edit { preferences ->
                preferences.remove(customerUserKey)
                preferences.remove(usernameKey)
                preferences.remove(firstNameKey)
                preferences.remove(lastNameKey)
                preferences.remove(phoneNumberKey)
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