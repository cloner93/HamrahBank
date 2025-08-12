package com.pmb.domain.usecae.card

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.card.RegisterCardRequest
import com.pmb.domain.model.card.RegisterCardResponse
import com.pmb.domain.repository.card.CardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterCardUseCase @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseUseCase<RegisterCardRequest, RegisterCardResponse>() {
    override suspend fun execute(params: RegisterCardRequest): Flow<Result<RegisterCardResponse>> {
        return cardsRepository.registerCard(params)
    }
}