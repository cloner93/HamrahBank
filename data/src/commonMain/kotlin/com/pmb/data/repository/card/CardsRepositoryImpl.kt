package com.pmb.data.repository.card

import com.pmb.core.platform.Result
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.data.mapper.cardService.toDomain
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.CardModel
import com.pmb.domain.repository.card.CardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider
) : CardsRepository {
    override fun getCardList(): Flow<Result<List<CardModel>>> {
        return remoteServiceProvider.getCardService().getCardList().mapApiResult {
            it.second.toDomain()
        }
    }
}
