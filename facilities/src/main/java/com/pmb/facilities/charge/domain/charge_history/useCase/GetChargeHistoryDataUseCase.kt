package com.pmb.facilities.charge.domain.charge_history.useCase

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryEntity
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryParams
import com.pmb.facilities.charge.domain.charge_history.reository.ChargeHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChargeHistoryDataUseCase @Inject constructor(
    private val chargeHistoryRepository: ChargeHistoryRepository
) : BaseUseCase<ChargeHistoryParams, ChargeHistoryEntity>() {
    override suspend fun execute(params: ChargeHistoryParams): Flow<Result<ChargeHistoryEntity>> {
        return chargeHistoryRepository.getChargeHistoryData(params)
    }
}