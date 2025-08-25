package com.pmb.domain.repository.card

import com.pmb.core.platform.Result
import com.pmb.domain.model.CardModel
import kotlinx.coroutines.flow.Flow

interface CardsRepository {
    fun getCardList(): Flow<Result<List<CardModel>>>
}
