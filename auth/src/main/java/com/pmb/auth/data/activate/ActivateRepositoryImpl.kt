package com.pmb.auth.data.activate

import com.pmb.auth.domain.activate.entity.ActivateEntity
import com.pmb.auth.domain.activate.entity.ActivateParams
import com.pmb.auth.domain.activate.repository.ActivateRepository
import com.pmb.ballon.models.AccountSampleModel
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ActivateRepositoryImpl @Inject constructor() : ActivateRepository {
    override suspend fun activeUser(activateParams: ActivateParams): Flow<Result<ActivateEntity>> =
        flow {
            val accountSampleModel = AccountSampleModel()
            emit(Result.Loading)
            delay(2000)
            activateParams.takeIf {
                it.nationalId == accountSampleModel.nationalId
                        && it.mobileNumber == accountSampleModel.mobileNumber
                        && it.password == accountSampleModel.passWord
                        && it.userName == accountSampleModel.userName
            }
                ?.let {
                    emit(Result.Success(ActivateEntity(isSuccess = true)))
                } ?: run {
                emit(Result.Error(message = "Cannot active user"))
            }
        }
}