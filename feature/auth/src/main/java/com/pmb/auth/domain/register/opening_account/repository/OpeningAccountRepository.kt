package com.pmb.auth.domain.register.opening_account.repository

import com.pmb.auth.domain.register.opening_account.entity.OpeningAccountEntity
import com.pmb.auth.domain.register.opening_account.entity.OpeningAccountParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface OpeningAccountRepository {
    suspend fun sendOpeningAccountData(params: OpeningAccountParams): Flow<Result<OpeningAccountEntity>>
}