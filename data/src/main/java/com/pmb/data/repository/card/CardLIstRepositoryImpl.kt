package com.pmb.data.repository.card

import com.pmb.core.platform.Result
import com.pmb.data.appManager.AppManager
import com.pmb.data.mapper.cardService.toDomain
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.CardModel
import com.pmb.domain.repository.card.CardListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardLIstRepositoryImpl @Inject constructor(
    private val appManager: AppManager
) : CardListRepository {
    override fun getCardList(): Flow<Result<List<CardModel>>> {
        return appManager.getCardService().getCardList().mapApiResult {
            it.second.toDomain()
        }
    }
}
