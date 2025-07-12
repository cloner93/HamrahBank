package com.pmb.auth.domain.ekyc.open_account.repository

import com.pmb.auth.domain.ekyc.open_account.entity.OpenAccountEntity
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface OpenAccountRepository {
    fun openAccount(): Flow<Result<OpenAccountEntity>>
}