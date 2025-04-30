package com.pmb.facilities.bill.data

import com.pmb.core.platform.Result
import com.pmb.facilities.R
import com.pmb.facilities.bill.domain.bill.entity.BillsType
import com.pmb.facilities.bill.domain.bill.repository.BillRepository
import com.pmb.facilities.charge.domain.charge.entity.ChargeData
import com.pmb.facilities.charge.domain.charge.entity.ChargeEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BillRepositoryImpl @Inject constructor() : BillRepository {
    override fun getBillsData(): Flow<Result<ChargeEntity>> = flow {
        emit(Result.Loading)
        delay(500)
        emit(
            Result.Success(
                ChargeEntity(
                    isSuccess = true, data = listOf(
                        ChargeData(
                            id = 0,
                            imageString = R.drawable.ic_mci,
                            title = "قبض تلفن همراه",
                            subTitle = "۰۹۹۱۱۰۵۱۷۲۵",
                            type = BillsType.TELECOMMUNICATION_BILL
                        ),
                        ChargeData(
                            id = 1,
                            imageString = R.drawable.ic_gas,
                            title = "قبض گاز",
                            subTitle = "123456789",
                            type = BillsType.GAS
                        ),
                        ChargeData(
                            id = 2,
                            imageString = R.drawable.ic_electric,
                            title = "قبض برق",
                            subTitle = "326598741",
                            type = BillsType.ELECTRIC
                        )
                    )
                )
            )
        )
    }
}