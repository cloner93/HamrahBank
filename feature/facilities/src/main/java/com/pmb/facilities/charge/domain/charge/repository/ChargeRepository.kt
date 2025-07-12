package com.pmb.facilities.charge.domain.charge.repository

import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.charge.entity.ChargeEntity
import kotlinx.coroutines.flow.Flow

interface ChargeRepository {
    fun getLatestCharge(): Flow<Result<ChargeEntity>>
}