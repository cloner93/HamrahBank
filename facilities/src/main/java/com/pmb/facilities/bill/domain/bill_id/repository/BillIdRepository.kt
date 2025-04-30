package com.pmb.facilities.bill.domain.bill_id.repository

import com.pmb.facilities.bill.domain.bill_id.entity.BillIdEntity
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdParams
import kotlinx.coroutines.flow.Flow
import com.pmb.core.platform.Result
interface BillIdRepository {
    fun getBillIdData(billIdParams: BillIdParams): Flow<Result<BillIdEntity>>
}