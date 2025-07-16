package com.pmb.data.repository.auth

import com.pmb.core.platform.Result
import com.pmb.data.appManager.AppManager
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.repository.auth.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val appManager: AppManager
) : LoginRepository {
    override fun login(
        customerId: String,
        username: String,
        password: String
    ): Flow<Result<LoginResponse>> {
        return appManager.getAuthService()
            .login(customerId = customerId, username = username, password = password)
            .mapApiResult {
                it.second
            }
    }
}

