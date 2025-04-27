package com.pmb.facilities.charge.data.charge

import com.pmb.core.platform.Result
import com.pmb.facilities.R
import com.pmb.facilities.charge.domain.charge.entity.ChargeData
import com.pmb.facilities.charge.domain.charge.entity.ChargeEntity
import com.pmb.facilities.charge.domain.charge.repository.ChargeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChargeRepositoryImpl @Inject constructor() : ChargeRepository {
    override fun getLatestCharge(): Flow<Result<ChargeEntity>> = flow {
        emit(Result.Loading)
        delay(2000L)
        emit(
            Result.Success(
                ChargeEntity(
                    isSuccess = true, data = listOf(
                        ChargeData(
                            id = 0,
                            imageString = R.drawable.ic_irancell,
                            title = "ایرانسل",
                            subTitle = "۰۹۹۱۱۰۵۱۷۲۵",
                        ),
                        ChargeData(
                            id = 1,
                            imageString = R.drawable.ic_irancell,
                            title = "ایرانسل",
                            subTitle = "۰۹۹۲۴۹۲۰۷۹۰"
                        ),
                        ChargeData(
                            id = 2,
                            imageString = R.drawable.ic_irancell,
                            title = "ایرانسل",
                            subTitle = "09308160417"
                        )
                    )
                )
            )
        )
    }
}