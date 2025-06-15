package com.pmb.domain.repository.auth.login

import com.pmb.core.platform.Result
import com.pmb.domain.model.dto.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(username: String, password: String): Flow<Result<LoginResponse>>
}
