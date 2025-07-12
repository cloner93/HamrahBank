package com.pmb.auth.data.ekyc.open_account

import com.pmb.auth.domain.ekyc.open_account.entity.OpenAccountEntity
import com.pmb.auth.domain.ekyc.open_account.repository.OpenAccountRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OpenAccountRepositoryImpl @Inject constructor() : OpenAccountRepository {
    override fun openAccount(): Flow<Result<OpenAccountEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(Result.Success(OpenAccountEntity(isSuccess = true)))
    }
}