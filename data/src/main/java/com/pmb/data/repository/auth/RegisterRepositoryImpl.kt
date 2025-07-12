package com.pmb.data.repository.auth

import com.pmb.core.platform.Result
import com.pmb.data.appManager.AppManager
import com.pmb.domain.repository.auth.RegisterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val appManager: AppManager
) : RegisterRepository {
    override fun register(
        customerId: String,
        username: String,
        password: String
    ): Flow<Result<Boolean>> {
        return appManager.getAuthService()
            .register(customerId = customerId, username = username, password = password)
    }
}
