package com.pmb.auth.data.new_password

import com.pmb.auth.domain.new_password.entity.NewPasswordEntity
import com.pmb.auth.domain.new_password.entity.NewPasswordParams
import com.pmb.auth.domain.new_password.repository.NewPasswordRepository
import com.pmb.ballon.models.AccountSampleModel
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewPasswordRepositoryImpl @Inject constructor() : NewPasswordRepository {
    override fun changePassWord(newPasswordParams: NewPasswordParams): Flow<Result<NewPasswordEntity>> =
        flow {
            val accountSampleModel = AccountSampleModel()
            emit(Result.Loading)
            newPasswordParams.takeIf {
                it.userName == accountSampleModel.userName
                        && it.passWord == accountSampleModel.passWord
                        && it.mobileNumber == accountSampleModel.mobileNumber
            }
                ?.let {
                    emit(Result.Success(NewPasswordEntity(isSuccess = true)))
                } ?: run {
                emit(Result.Error(message = "Cannot change the password"))
            }
        }
}