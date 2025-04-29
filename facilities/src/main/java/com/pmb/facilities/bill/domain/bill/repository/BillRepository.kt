package com.pmb.facilities.bill.domain.bill.repository

import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.charge.entity.ChargeEntity
import kotlinx.coroutines.flow.Flow

interface BillRepository {
    fun getBillsData(): Flow<Result<ChargeEntity>>
}