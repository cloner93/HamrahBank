package com.pmb.auth.domain.scan_card_info.card_info.repository

import com.pmb.auth.domain.scan_card_info.card_info.entity.CardInfoEntity
import com.pmb.auth.domain.scan_card_info.card_info.entity.CardInfoParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface CardInfoRepository {
    suspend fun sendCardInfo(cardInfoParams: CardInfoParams):Flow<Result<CardInfoEntity>>
}