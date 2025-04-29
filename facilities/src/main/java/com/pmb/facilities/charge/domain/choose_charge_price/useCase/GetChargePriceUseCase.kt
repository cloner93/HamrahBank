package com.pmb.facilities.charge.domain.choose_charge_price.useCase

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.choose_charge_price.entity.ChooseChargePriceEntity
import com.pmb.facilities.charge.domain.choose_charge_price.repository.ChooseChargePriceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChargePriceUseCase @Inject constructor(
    private val chooseChargePriceRepository: ChooseChargePriceRepository
) : BaseUseCase<Unit, ChooseChargePriceEntity>() {
    override suspend fun execute(params: Unit): Flow<Result<ChooseChargePriceEntity>> {
        return chooseChargePriceRepository.getChargePrice()
    }
}