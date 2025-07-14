package com.pmb.data.appManager.persistence

import com.pmb.data.appManager.persistence.user.UserDataStore
import javax.inject.Inject

class PersistenceServiceImpl @Inject constructor(
    private val userDataStore: UserDataStore
) : PersistenceService {
    override fun getUserDataStore(): UserDataStore {
        return userDataStore
    }
}