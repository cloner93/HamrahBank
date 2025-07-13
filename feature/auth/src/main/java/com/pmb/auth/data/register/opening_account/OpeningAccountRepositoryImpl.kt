package com.pmb.auth.data.register.opening_account

import com.pmb.auth.domain.register.opening_account.entity.OpeningAccountEntity
import com.pmb.auth.domain.register.opening_account.entity.OpeningAccountParams
import com.pmb.auth.domain.register.opening_account.repository.OpeningAccountRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OpeningAccountRepositoryImpl @Inject constructor() : OpeningAccountRepository {
    override suspend fun sendOpeningAccountData(params: OpeningAccountParams): Flow<Result<OpeningAccountEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(OpeningAccountEntity(isSuccess = true)))
        }
}
