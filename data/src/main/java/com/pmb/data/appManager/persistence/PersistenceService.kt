package com.pmb.data.appManager.persistence

import com.pmb.data.appManager.persistence.user.UserDataStore

interface PersistenceService {
    fun getUserDataStore(): UserDataStore
}