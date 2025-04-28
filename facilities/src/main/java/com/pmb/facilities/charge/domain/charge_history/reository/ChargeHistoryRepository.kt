package com.pmb.facilities.charge.domain.charge_history.reository

import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryEntity
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryFilterListEntity
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryParams
import kotlinx.coroutines.flow.Flow

interface ChargeHistoryRepository {
    fun getFilterData(): Flow<Result<ChargeHistoryFilterListEntity>>
    fun getChargeHistoryData(filter: ChargeHistoryParams): Flow<Result<ChargeHistoryEntity>>
}