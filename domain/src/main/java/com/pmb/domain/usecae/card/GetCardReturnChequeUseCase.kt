package com.pmb.domain.usecae.card

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.card.ReturnCardChequeResponse
import com.pmb.domain.repository.card.CardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCardReturnChequeUseCase @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseUseCase<Unit, ReturnCardChequeResponse>() {
    override suspend fun execute(params: Unit): Flow<Result<ReturnCardChequeResponse>> {
        return cardsRepository.getReturnCheque()
    }
}