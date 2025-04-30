package com.pmb.facilities.bill.data

import com.pmb.core.platform.Result
import com.pmb.facilities.R
import com.pmb.facilities.bill.domain.bill_id.entity.BillDetails
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdEntity
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdParams
import com.pmb.facilities.bill.domain.bill_id.repository.BillIdRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BillIdRepositoryImpl @Inject constructor() : BillIdRepository {
    override fun getBillIdData(billIdParams: BillIdParams): Flow<Result<BillIdEntity>> = flow {
        emit(Result.Loading)
        delay(100)
        emit(
            Result.Success(
                when (billIdParams.billId) {
                    "123456789" -> {
                        BillIdEntity(
                            billImage = R.drawable.ic_gas,
                            billTitle = "قبض گاز",
                            billId = "326598741",
                            billPrice = 1265879.toDouble(),
                            billPriceTitle = "مبلغ بدهی",
                            billDetails = BillDetails(
                                billCustomer = "بهسازان ملت",
                                address = "دیباجی جنوبی کوچه مژگان",
                                expireDate = "1404/02/15"
                            )
                        )
                    }

                    "326598741" -> {
                        BillIdEntity(
                            billImage = R.drawable.ic_electric,
                            billTitle = "قبض برق",
                            billId = "326598741",
                            billPrice = 697852.toDouble(),
                            billPriceTitle = "مبلغ بدهی",
                            billDetails = BillDetails(
                                billCustomer = "نیلوفر طائفی",
                                address = "خ فاطمی ک داریوش بن بست اول پ ۱۲۰",
                                expireDate = "1404/02/15"
                            )
                        )
                    }

                    else -> BillIdEntity(
                        billImage = R.drawable.ic_mci,
                        billTitle = "قبض تلفن همراه",
                        billId = "65487998",
                        billPrice = 2356584.toDouble(),
                        billPriceTitle = "مبلغ بدهی",
                        billDetails = BillDetails(
                            billCustomer = "فائزه رحمانی",
                            address = "دیباجی جنوبی شمسایی",
                            expireDate = "1404/02/15"
                        )
                    )
                }
            )
        )
    }
}