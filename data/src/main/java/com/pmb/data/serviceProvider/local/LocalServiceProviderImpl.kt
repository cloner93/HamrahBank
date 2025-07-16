package com.pmb.data.serviceProvider.local

import com.pmb.data.serviceProvider.local.user.UserDataStore
import javax.inject.Inject

class LocalServiceProviderImpl @Inject constructor(
    private val userDataStore: UserDataStore
) : LocalServiceProvider {
    override fun getUserDataStore(): UserDataStore {
        return userDataStore
    }
}