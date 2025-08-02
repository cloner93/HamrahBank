package com.pmb.data.serviceProvider.local.user

import com.pmb.domain.model.UserData

interface UserDataStore {
    suspend fun setUserData(userData: UserData)
    suspend fun getUserData(): UserData
}