package com.pmb.auth.domain.scan_card_info.card_info.useCase

import com.pmb.auth.domain.scan_card_info.card_info.entity.CardInfoEntity
import com.pmb.auth.domain.scan_card_info.card_info.entity.CardInfoParams
import com.pmb.auth.domain.scan_card_info.card_info.repository.CardInfoRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendCardInfoUseCase @Inject constructor(
    private val cardInfoRepository: CardInfoRepository
) : BaseUseCase<CardInfoParams, CardInfoEntity>() {
    override suspend fun execute(params: CardInfoParams): Flow<Result<CardInfoEntity>> =
        cardInfoRepository.sendCardInfo(params)
}