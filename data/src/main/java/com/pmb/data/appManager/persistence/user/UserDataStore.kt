package com.pmb.data.appManager.persistence.user

import com.pmb.domain.model.UserData

interface UserDataStore {
    suspend fun setUserData(userData: UserData)
    suspend fun getUserData(): UserData?
}