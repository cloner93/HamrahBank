package com.pmb.facilities.charge.domain.charge.useCase

import com.pmb.core.platform.BaseUseCase
import com.pmb.facilities.charge.domain.charge.entity.ChargeEntity
import com.pmb.facilities.charge.domain.charge.repository.ChargeRepository
import javax.inject.Inject

class GetLatestChargeUseCase @Inject constructor(
    private val chargeRepository: ChargeRepository
) : BaseUseCase<Unit, ChargeEntity>() {
    override suspend fun execute(params: Unit) = chargeRepository.getLatestCharge()
}