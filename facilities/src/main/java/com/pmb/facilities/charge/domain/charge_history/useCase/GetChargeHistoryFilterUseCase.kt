package com.pmb.facilities.charge.domain.charge_history.useCase

import com.pmb.core.platform.BaseUseCase
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryFilterListEntity
import com.pmb.facilities.charge.domain.charge_history.reository.ChargeHistoryRepository
import javax.inject.Inject

class GetChargeHistoryFilterUseCase @Inject constructor(
    private val chargeHistoryRepository: ChargeHistoryRepository
) : BaseUseCase<Unit, ChargeHistoryFilterListEntity>() {
    override suspend fun execute(params: Unit) = chargeHistoryRepository.getFilterData()

}