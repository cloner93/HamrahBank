package com.pmb.domain.repository

import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import kotlinx.coroutines.flow.Flow

interface DepositsRepository {
    fun getDepositList(): Flow<Result<List<DepositModel>>>
}
