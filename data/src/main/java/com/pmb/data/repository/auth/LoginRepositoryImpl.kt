package com.pmb.data.repository.auth

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.LoginRequest
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.repository.auth.LoginRepository
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val client: NetworkManger
) : LoginRepository {
    override fun login(
        customerId: String,
        username: String,
        password: String
    ): Flow<Result<LoginResponse>> {
        val req = LoginRequest(customerId = customerId, username = username, password = password)

        return client.request<LoginRequest, LoginResponse>(endpoint = "login", data = req)
            .mapApiResult { /*metaData, data ->*/
                it.second.toDomain()
            }
    }
}

//0912
fun LoginResponse.toDomain(): LoginResponse {
    // TODO: complete this
    return this
}