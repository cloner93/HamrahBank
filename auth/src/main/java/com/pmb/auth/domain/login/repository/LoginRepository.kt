package com.pmb.auth.domain.login.repository

import com.pmb.auth.domain.login.entity.UserEntity
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(username:String,password:String): Flow<Result<UserEntity>>
}