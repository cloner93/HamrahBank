package com.pmb.domain.repository.auth

import com.pmb.core.platform.Result
import com.pmb.domain.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(customerId: String, username: String, password: String): Flow<Result<LoginResponse>>
}
