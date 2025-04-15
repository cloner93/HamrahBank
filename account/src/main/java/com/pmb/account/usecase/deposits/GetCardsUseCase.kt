package com.pmb.account.usecase.deposits

import com.pmb.account.presentation.component.CardModel
import com.pmb.account.tempRepo.CardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(
    private val cardsRepository: CardsRepository
) {
    suspend operator fun invoke(): List<CardModel> =
        withContext(Dispatchers.IO) {
            try {
                cardsRepository.getCards()
            } catch (e: Exception) {
                throw e
            }
        }
}