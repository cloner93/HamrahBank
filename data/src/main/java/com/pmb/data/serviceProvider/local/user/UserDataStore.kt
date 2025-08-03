package com.pmb.data.serviceProvider.local.user

import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.UserData

interface UserDataStore {
    suspend fun setUserData(userData: UserData)
    suspend fun getUserData(): UserData
    suspend fun logoutUser(): Boolean
    suspend fun setDepositAsMainDeposit(deposit: DepositModel): Boolean
    suspend fun getMainDeposit(): DepositModel?
}