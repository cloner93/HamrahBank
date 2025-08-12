package com.pmb.domain.usecae.card

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.card.FetchCommissionForCreateCardResponse
import com.pmb.domain.repository.card.CardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCommissionForCreateUseCase @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseUseCase<FetchCommissionForCreateParams, FetchCommissionForCreateCardResponse>() {
    override suspend fun execute(params: FetchCommissionForCreateParams): Flow<Result<FetchCommissionForCreateCardResponse>> {
        return cardsRepository.fetchCommissionForCreateCard(
            cardGroup = params.cardGroup,
            accountNumber = params.accountNumber
        )
    }
}

data class FetchCommissionForCreateParams(
    val cardGroup: Int,
    val accountNumber: Int
)