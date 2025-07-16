package com.pmb.auth.data.activation.activate

import com.pmb.auth.domain.activation.activate.entity.ActivateEntity
import com.pmb.auth.domain.activation.activate.entity.ActivateParams
import com.pmb.auth.domain.activation.activate.repository.ActivateRepository
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
            activateParams.takeIf {
                it.mobileNumber == accountSampleModel.mobileNumber
            }
                ?.let {
                    emit(Result.Success(ActivateEntity(isSuccess = true)))
                } ?: run {
                emit(Result.Error(message = "Cannot active user"))
            }
        }
}