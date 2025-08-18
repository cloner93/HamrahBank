package com.pmb.domain.usecae.card

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.card.CardCustomerAddressRequest
import com.pmb.domain.model.card.CardCustomerAddressResponse
import com.pmb.domain.repository.card.CardsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetCustomerAddressForCardUseCase @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseUseCase<CardCustomerAddressParams, CardCustomerAddressResponse>() {
    override suspend fun execute(params: CardCustomerAddressParams): Flow<Result<CardCustomerAddressResponse>> {
        return cardsRepository.getCustomerAddress(params.toRequest())
    }
}

data class CardCustomerAddressParams(
    val accountNumber: Long,
    val cardGroup: Long,
) {
    fun toRequest(): CardCustomerAddressRequest {
        return CardCustomerAddressRequest(
            accountNumber = accountNumber,
            cardGroup = cardGroup,
        )
    }
}