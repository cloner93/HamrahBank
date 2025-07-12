package com.pmb.facilities.charge.domain.purchase_charge.repository

import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.purchase_charge.entity.PurchaseChargeEntity
import kotlinx.coroutines.flow.Flow

interface PurchaseChargeRepository{
    fun getOperator(): Flow<Result<PurchaseChargeEntity>>
}