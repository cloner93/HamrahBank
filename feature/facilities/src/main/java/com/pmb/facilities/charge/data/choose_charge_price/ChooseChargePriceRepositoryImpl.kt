package com.pmb.facilities.charge.data.choose_charge_price

import androidx.compose.runtime.mutableStateOf
import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.choose_charge_price.entity.ChooseChargePriceEntity
import com.pmb.facilities.charge.domain.choose_charge_price.entity.ChoosePrice
import com.pmb.facilities.charge.domain.choose_charge_price.repository.ChooseChargePriceRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChooseChargePriceRepositoryImpl @Inject constructor() : ChooseChargePriceRepository {
    override fun getChargePrice(): Flow<Result<ChooseChargePriceEntity>> = flow {
        emit(Result.Loading)
        delay(500)
        emit(
            Result.Success(
                ChooseChargePriceEntity(
                    isSuccess = true,
                    data = listOf(
                        ChoosePrice(
                            id = 0, "500000", isChecked = mutableStateOf(
                                false
                            )
                        ),
                        ChoosePrice(
                            id = 1, "1000000", isChecked = mutableStateOf(
                                false
                            )
                        ),
                        ChoosePrice(
                            id = 2, "2000000", isChecked = mutableStateOf(
                                false
                            )
                        ),
                        ChoosePrice(
                            id = 3, "5000000", isChecked = mutableStateOf(
                                false
                            )
                        ),
                        ChoosePrice(
                            id = 4, "10000000", isChecked = mutableStateOf(
                                false
                            )
                        ),
                    )
                )
            )
        )
    }
}