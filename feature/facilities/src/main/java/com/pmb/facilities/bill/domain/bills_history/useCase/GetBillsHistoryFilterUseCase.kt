package com.pmb.facilities.bill.domain.bills_history.useCase

import com.pmb.core.platform.BaseUseCase
import com.pmb.facilities.bill.domain.bills_history.repository.BillsHistoryRepository
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryFilterListEntity
import javax.inject.Inject

class GetBillsHistoryFilterUseCase @Inject constructor(
    private val billsHistoryRepository: BillsHistoryRepository
) : BaseUseCase<Unit, ChargeHistoryFilterListEntity>() {
    override suspend fun execute(params: Unit) = billsHistoryRepository.getFilterData()

}