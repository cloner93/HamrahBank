package com.pmb.data.repository.auth.login

import com.pmb.core.platform.Result
import com.pmb.domain.model.dto.LoginRequest
import com.pmb.domain.model.dto.LoginResponse
import com.pmb.domain.repository.auth.login.LoginRepository
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val client: NetworkManger
) : LoginRepository {
    override fun login(username: String, password: String): Flow<Result<LoginResponse>> {
        val req = LoginRequest(username = username, password = password)

        return client.request(endpoint = "login", data = req)
    }
}
