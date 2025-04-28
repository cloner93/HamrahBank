package com.pmb.facilities.charge.domain.purchase_charge.useCase

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.purchase_charge.entity.PurchaseChargeEntity
import com.pmb.facilities.charge.domain.purchase_charge.repository.PurchaseChargeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOperatorUseCase @Inject constructor(
    private val purchaseChargeRepository: PurchaseChargeRepository
) : BaseUseCase<Unit, PurchaseChargeEntity>(){
    override suspend fun execute(params: Unit): Flow<Result<PurchaseChargeEntity>> {
        return purchaseChargeRepository.getOperator()
    }
}