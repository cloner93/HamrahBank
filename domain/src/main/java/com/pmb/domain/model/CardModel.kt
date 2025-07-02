package com.pmb.domain.model

data class CardModel(
    val cardNumber: String,
    val cardType: CardType,
    val amount: Double,
    val currency: String,
    val placeholder: String,
    val expiredDate: String
)

enum class CardType(val cardType: String) {
    MELLAT_CARD("ملت کارت"), BON_CARD("بن کارت"), SPORT_BON_CARD("بن کارت ورزشی")
}