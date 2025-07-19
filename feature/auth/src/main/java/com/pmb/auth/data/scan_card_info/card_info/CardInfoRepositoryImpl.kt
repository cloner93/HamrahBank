package com.pmb.auth.data.scan_card_info.card_info

import com.pmb.auth.domain.scan_card_info.card_info.entity.CardInfoEntity
import com.pmb.auth.domain.scan_card_info.card_info.entity.CardInfoParams
import com.pmb.auth.domain.scan_card_info.card_info.repository.CardInfoRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CardInfoRepositoryImpl @Inject constructor() : CardInfoRepository {
    override suspend fun sendCardInfo(cardInfoParams: CardInfoParams): Flow<Result<CardInfoEntity>> =
        flow {
            emit(Result.Loading)
            emit(Result.Success(CardInfoEntity(isSuccess = true)))
        }
}