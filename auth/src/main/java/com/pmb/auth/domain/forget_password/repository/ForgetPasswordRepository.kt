package com.pmb.auth.domain.forget_password.repository

import com.pmb.auth.domain.forget_password.entity.ForgetPasswordEntity
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow


interface ForgetPasswordRepository {
    suspend fun forgetPassword(mobileNumber:String,nationalId:String,password:String): Flow<Result<ForgetPasswordEntity>>
}