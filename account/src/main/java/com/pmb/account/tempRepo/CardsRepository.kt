package com.pmb.account.tempRepo

import com.pmb.account.presentation.component.CardModel
import com.pmb.account.presentation.component.CardType
import javax.inject.Inject

class CardsRepository @Inject constructor() {
    suspend fun getCards(): List<CardModel> {
        return listOf(
            CardModel(
                cardNumber = "6037991234567890",
                cardType = CardType.MELLAT_CARD,
                amount = 125000.0,
                currency = "ریال",
                placeholder = "علی رضایی",
                expiredDate = "03/27"
            ),
            CardModel(
                cardNumber = "6104339876543210",
                amount = 875000.0,
                currency = "ریال",
                placeholder = "مریم احمدی",
                cardType = CardType.BON_CARD,
                expiredDate = "11/26"
            ),
            CardModel(
                cardNumber = "6273539988776655",
                amount = 50000.0,
                cardType = CardType.SPORT_BON_CARD,
                currency = "ریال",
                placeholder = "سمیرا کرمی",
                expiredDate = "09/28"
            )
        )
    }
}