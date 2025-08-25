package com.pmb.data.serviceProvider.local

import com.pmb.data.serviceProvider.local.biometric.BiometricDataStore
import com.pmb.data.serviceProvider.local.user.UserDataStore
import javax.inject.Inject

class LocalServiceProviderImpl @Inject constructor(
    private val userDataStore: UserDataStore,
    private val biometricDataStore: BiometricDataStore
) : LocalServiceProvider {
    override fun getUserDataStore(): UserDataStore {
        return userDataStore
    }

    override fun getBiometric(): BiometricDataStore {
        return biometricDataStore
    }
}