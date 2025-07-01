package com.pmb.data.repository.auth

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.RegisterRequest
import com.pmb.domain.repository.auth.RegisterRepository
import com.pmb.model.ResponseMetaData
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val client: NetworkManger
) : RegisterRepository {
    override fun register(
        customerId: String,
        username: String,
        password: String
    ): Flow<Result<Boolean>> {
        val req = RegisterRequest(
            customerId = customerId,
            userName = username,
            password = password,
            vcode = 0
        )

        return client.request<RegisterRequest, Unit>("register", req)
            .mapApiResult { /*metaData, data ->*/
                it.first.toDomain()
            }
    }
}

fun ResponseMetaData?.toDomain(): Boolean {
//    if (this)
    return this?.statusMessage?.takeIf { it == "موفق" }?.let { true } ?: run { false }
}