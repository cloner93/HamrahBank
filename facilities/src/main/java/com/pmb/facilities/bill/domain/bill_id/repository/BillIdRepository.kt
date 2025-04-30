package com.pmb.facilities.bill.domain.bill_id.repository

import com.pmb.core.platform.Result
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdEntity
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdParams
import com.pmb.facilities.bill.domain.bill_id.entity.TeleCommunicationEntity
import kotlinx.coroutines.flow.Flow

interface BillIdRepository {
    fun getBillIdData(billIdParams: BillIdParams): Flow<Result<BillIdEntity>>
    fun getTeleBillData(params: BillIdParams): Flow<Result<TeleCommunicationEntity>>
}