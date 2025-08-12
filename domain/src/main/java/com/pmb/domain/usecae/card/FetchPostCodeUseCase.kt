package com.pmb.domain.usecae.card

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.card.CardFetchPostCodeResponse
import com.pmb.domain.repository.card.CardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPostCodeUseCase @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseUseCase<Int, CardFetchPostCodeResponse>() {
    override suspend fun execute(params: Int): Flow<Result<CardFetchPostCodeResponse>> {
        return cardsRepository.fetchPostCodeCard(params)
    }
}