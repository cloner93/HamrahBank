package com.pmb.facilities.bill.domain.bill.useCase

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.facilities.bill.domain.bill.repository.BillRepository
import com.pmb.facilities.charge.domain.charge.entity.ChargeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBillsDataUseCase @Inject constructor(private val billRepository: BillRepository) :
    BaseUseCase<Unit, ChargeEntity>() {
    override suspend fun execute(params: Unit): Flow<Result<ChargeEntity>> {
        return billRepository.getBillsData()
    }
}