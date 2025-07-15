package com.pmb.data.serviceProvider.local

import com.pmb.data.serviceProvider.local.user.UserDataStore

interface LocalServiceProvider {
    fun getUserDataStore(): UserDataStore
}