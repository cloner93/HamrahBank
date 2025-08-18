package com.pmb.domain.usecae.card

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.FetchCardFormatResponse
import com.pmb.domain.repository.card.CardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCardFormatForCreatingUseCase @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseUseCase<Unit, List<FetchCardFormatResponse>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<FetchCardFormatResponse>>> {
        return cardsRepository.fetchCardFormat()
    }
}