package com.pmb.data.appManager.deposit

import com.pmb.core.platform.Result
import com.pmb.domain.model.Deposit
import com.pmb.model.SuccessData
import kotlinx.coroutines.flow.Flow

interface DepositService {
    fun getDepositList(): Flow<Result<SuccessData<List<Deposit>>>>
}