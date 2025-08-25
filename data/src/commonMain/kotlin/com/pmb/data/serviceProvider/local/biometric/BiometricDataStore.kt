package com.pmb.data.serviceProvider.local.biometric

interface BiometricDataStore {
    suspend fun getBiometricState(): Boolean
    suspend fun setBiometricState(isEnabled: Boolean)
}