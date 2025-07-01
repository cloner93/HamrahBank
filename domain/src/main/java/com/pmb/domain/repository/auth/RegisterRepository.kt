package com.pmb.domain.repository.auth

import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    fun register(
        customerId: String,
        username: String,
        password: String
    ): Flow<Result<Boolean>>
}