package com.pmb.facilities.charge.data.charge_history

import androidx.compose.runtime.mutableStateOf
import com.pmb.core.platform.Result
import com.pmb.facilities.R
import com.pmb.facilities.charge.domain.charge.entity.ChargeData
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryEntity
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryFilterEntity
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryFilterListEntity
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryParams
import com.pmb.facilities.charge.domain.charge_history.reository.ChargeHistoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChargeHistoryRepositoryImpl @Inject constructor() : ChargeHistoryRepository {
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
                            filterName = "ماه مهر"
                        ),
                        ChargeHistoryFilterEntity(
                            isSelected = mutableStateOf(false),
                            filterName = "ماه آبان"
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
                                        subTitle = "ایرانسل",
                                        title = "۰۹۹۱۱۰۵۱۷۲۵",
                                        price = "200000"
                                    ),
                                    ChargeData(
                                        id = 1,
                                        imageString = R.drawable.ic_mci,
                                        subTitle = "ایرانسل",
                                        title = "۰۹۹۲۴۹۲۰۷۹۰",
                                        price = "100000"
                                    ),
                                    ChargeData(
                                        id = 2,
                                        imageString = R.drawable.ic_rightel,
                                        subTitle = "ایرانسل",
                                        title = "09308160417",
                                        price = "5000000"
                                    ),
                                    ChargeData(
                                        id = 2,
                                        imageString = R.drawable.ic_rightel,
                                        subTitle = "ایرانسل",
                                        title = "09308160417",
                                        price = "5000000"
                                    ),
                                    ChargeData(
                                        id = 2,
                                        imageString = R.drawable.ic_rightel,
                                        subTitle = "ایرانسل",
                                        title = "09308160417",
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
                                        id = 0,
                                        imageString = R.drawable.ic_irancell,
                                        subTitle = "ایرانسل",
                                        title = "۰۹۹۱۱۰۵۱۷۲۵",
                                        price = "200000"
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
                                        imageString = R.drawable.ic_rightel,
                                        subTitle = "ایرانسل",
                                        title = "09308160417",
                                        price = "5000000"
                                    ),
                                )
                            )
                        )
                    )
                }

                "ماه مهر" -> {
                    emit(
                        Result.Success(
                            ChargeHistoryEntity(
                                isSuccess = true, data = listOf(
                                    ChargeData(
                                        id = 2,
                                        imageString = R.drawable.ic_rightel,
                                        subTitle = "ایرانسل",
                                        title = "09308160417",
                                        price = "5000000"
                                    ),
                                )
                            )
                        )
                    )
                }

                "ماه آبان" -> {
                    emit(
                        Result.Success(
                            ChargeHistoryEntity(
                                isSuccess = true, data = listOf(
                                    ChargeData(
                                        id = 2,
                                        imageString = R.drawable.ic_rightel,
                                        subTitle = "ایرانسل",
                                        title = "09308160417",
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