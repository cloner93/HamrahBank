package com.pmb.auth.data.first_login

import com.pmb.auth.domain.first_login.entity.FirstLoginStepRequest
import com.pmb.auth.domain.first_login.entity.FirstLoginStepResponse
import com.pmb.auth.domain.first_login.repository.FirstLoginRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirstLoginRepositoryImpl @Inject constructor() : FirstLoginRepository {
    override suspend fun firstLoginStepConfirmation(
        firstLoginStepRequest: FirstLoginStepRequest
    ): Flow<Result<FirstLoginStepResponse>> = flow {
        emit(Result.Loading)
        delay(2000)
        firstLoginStepRequest.takeIf { it.mobileNumber == "09308160417" && it.password == "mellat" && it.userName == "mellat" }
            ?.let {
                emit(Result.Success(FirstLoginStepResponse(isSuccess = true)))
            } ?: run {
            emit(Result.Error(message = "UserName and password are incorrect"))
        }
    }
}