package com.pmb.auth.domain.ekyc.openAccount.repository

import com.pmb.auth.domain.ekyc.openAccount.entity.OpenAccountEntity
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface OpenAccountRepository {
    fun openAccount(): Flow<Result<OpenAccountEntity>>
}