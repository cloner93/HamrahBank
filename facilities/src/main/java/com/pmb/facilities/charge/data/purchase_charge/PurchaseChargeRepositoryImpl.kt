package com.pmb.facilities.charge.data.purchase_charge

import com.pmb.core.platform.Result
import com.pmb.facilities.R
import com.pmb.facilities.charge.domain.purchase_charge.entity.Operator
import com.pmb.facilities.charge.domain.purchase_charge.entity.PurchaseChargeEntity
import com.pmb.facilities.charge.domain.purchase_charge.repository.PurchaseChargeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PurchaseChargeRepositoryImpl @Inject constructor() : PurchaseChargeRepository {
    override fun getOperator(): Flow<Result<PurchaseChargeEntity>> = flow {
        emit(Result.Loading)
        delay(50)
        emit(
            Result.Success(
                PurchaseChargeEntity(
                    isSuccess = true,
                    data = listOf(
                        Operator(
                            id = 0,
                            operator = "همراه اول",
                            operatorImage = R.drawable.ic_mci,
                        ),
                        Operator(
                            id = 1,
                            operator = "ایرانسل",
                            operatorImage = R.drawable.ic_irancell,
                        ),
                        Operator(
                            id = 2,
                            operator = "رایتل",
                            operatorImage = R.drawable.ic_rightel,
                        ),
                    )
                )
            )
        )
    }
}