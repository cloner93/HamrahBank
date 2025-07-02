package com.pmb.domain.usecae.card

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.CardModel
import com.pmb.domain.repository.card.CardListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserCardListUseCase @Inject constructor(
    private val cardLIstRepository: CardListRepository
) : BaseUseCase<Unit, List<CardModel>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<CardModel>>> {
        return cardLIstRepository.getCardList()
    }
}