package com.pmb.auth.data.forget_password

import com.pmb.auth.domain.forget_password.entity.ForgetPasswordEntity
import com.pmb.auth.domain.forget_password.repository.ForgetPasswordRepository
import com.pmb.ballon.models.AccountSampleModel
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ForgetPasswordRepositoryImpl @Inject constructor(

) : ForgetPasswordRepository {
    override suspend fun forgetPassword(
        mobileNumber: String,
        nationalId: String,
        password: String
    ): Flow<Result<ForgetPasswordEntity>> = flow {
        val accountSampleModel = AccountSampleModel()
        emit(Result.Loading)
        delay(2000)
        if (mobileNumber == accountSampleModel.mobileNumber) {
            emit(Result.Success(ForgetPasswordEntity(isSuccess = true)))
        } else {
            emit(Result.Error(message = "forget password failed"))
        }
    }
}