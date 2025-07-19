package com.pmb.facilities.bill.domain.bill_id.useCase

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdEntity
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdParams
import com.pmb.facilities.bill.domain.bill_id.repository.BillIdRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBillDataBasedIdUseCase @Inject constructor(private val billIdRepository: BillIdRepository) :
    BaseUseCase<BillIdParams, BillIdEntity>() {
    override suspend fun execute(params: BillIdParams): Flow<Result<BillIdEntity>> {
        return billIdRepository.getBillIdData(params)
    }
}