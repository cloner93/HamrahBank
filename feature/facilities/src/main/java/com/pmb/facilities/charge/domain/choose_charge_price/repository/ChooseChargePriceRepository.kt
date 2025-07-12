package com.pmb.facilities.charge.domain.choose_charge_price.repository

import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.choose_charge_price.entity.ChooseChargePriceEntity
import kotlinx.coroutines.flow.Flow

interface ChooseChargePriceRepository {
    fun getChargePrice(): Flow<Result<ChooseChargePriceEntity>>
}