package com.pmb.facilities.bill.data

import androidx.compose.runtime.mutableStateOf
import com.pmb.core.platform.Result
import com.pmb.facilities.R
import com.pmb.facilities.bill.domain.bills_history.repository.BillsHistoryRepository
import com.pmb.facilities.charge.domain.charge.entity.ChargeData
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryEntity
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryFilterEntity
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryFilterListEntity
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryParams
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BillsHistoryRepositoryImpl @Inject constructor() : BillsHistoryRepository {
    override fun getFilterData(): Flow<Result<ChargeHistoryFilterListEntity>> = flow {
        emit(Result.Loading)
        delay(500)
        emit(
            Result.Success(
                ChargeHistoryFilterListEntity(
                    isSuccess = true, chargeFilterList = listOf(
                        ChargeHistoryFilterEntity(
                            isSelected = mutableStateOf(true),
                            filterName = "همه"
                        ),
                        ChargeHistoryFilterEntity(
                            isSelected = mutableStateOf(false),
                            filterName = "هفته گذشته"
                        ),
                        ChargeHistoryFilterEntity(
                            isSelected = mutableStateOf(false),
                            filterName = "ماه گذشته"
                        ),
                        ChargeHistoryFilterEntity(
                            isSelected = mutableStateOf(false),
                            filterName = "ماه بهمن"
                        ),
                        ChargeHistoryFilterEntity(
                            isSelected = mutableStateOf(false),
                            filterName = "ماه اسفند"
                        ),
                    )
                )
            )
        )
    }

    override fun getChargeHistoryData(filter: ChargeHistoryParams): Flow<Result<ChargeHistoryEntity>> =
        flow {
            emit(Result.Loading)
            delay(500)
            when (filter.filter) {
                "همه" -> {
                    emit(
                        Result.Success(
                            ChargeHistoryEntity(
                                isSuccess = true, data = listOf(
                                    ChargeData(
                                        id = 0,
                                        imageString = R.drawable.ic_irancell,
                                        subTitle = "امروز ساعت 15:48",
                                        title = "قبض ایرانسل",
                                        price = "200000"
                                    ),
                                    ChargeData(
                                        id = 1,
                                        imageString = R.drawable.ic_gas,
                                        subTitle = "دیروز ساعت 20:30",
                                        title = "قبض گاز",
                                        price = "100000"
                                    ),
                                    ChargeData(
                                        id = 2,
                                        imageString = R.drawable.ic_electric,
                                        subTitle = "18 بهمن 1403",
                                        title = "قبض برق",
                                        price = "5000000"
                                    ),
                                    ChargeData(
                                        id = 2,
                                        imageString = R.drawable.ic_mci,
                                        subTitle = "شنبه 20 اسفند 1403",
                                        title = "قبض همراه اول",
                                        price = "5000000"
                                    ),
                                )
                            )
                        )
                    )
                }

                "هفته گذشته" -> {
                    emit(
                        Result.Success(
                            ChargeHistoryEntity(
                                isSuccess = true, data = listOf(
                                    ChargeData(
                                        id = 1,
                                        imageString = R.drawable.ic_gas,
                                        subTitle = "دیروز ساعت 20:30",
                                        title = "قبض گاز",
                                        price = "100000"
                                    ),
                                )
                            )
                        )
                    )
                }

                "ماه گذشته" -> {
                    emit(
                        Result.Success(
                            ChargeHistoryEntity(
                                isSuccess = true, data = listOf(
                                    ChargeData(
                                        id = 2,
                                        imageString = R.drawable.ic_mci,
                                        subTitle = "شنبه 20 اسفند 1403",
                                        title = "قبض همراه اول",
                                        price = "5000000"
                                    ),
                                )
                            )
                        )
                    )
                }

                "ماه بهمن" -> {
                    emit(
                        Result.Success(
                            ChargeHistoryEntity(
                                isSuccess = true, data = listOf(
                                    ChargeData(
                                        id = 2,
                                        imageString = R.drawable.ic_electric,
                                        subTitle = "18 بهمن 1403",
                                        title = "قبض برق",
                                        price = "5000000"
                                    ),
                                )
                            )
                        )
                    )
                }

                "ماه اسفند" -> {
                    emit(
                        Result.Success(
                            ChargeHistoryEntity(
                                isSuccess = true, data = listOf(
                                    ChargeData(
                                        id = 2,
                                        imageString = R.drawable.ic_mci,
                                        subTitle = "شنبه 20 اسفند 1403",
                                        title = "قبض همراه اول",
                                        price = "5000000"
                                    ),
                                )
                            )
                        )
                    )
                }
            }
        }
}