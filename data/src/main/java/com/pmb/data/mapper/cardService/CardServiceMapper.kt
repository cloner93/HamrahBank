package com.pmb.data.mapper.cardService

import com.pmb.domain.model.Card
import com.pmb.domain.model.CardModel
import com.pmb.domain.model.CardType

fun List<Card>.toDomain(): List<CardModel> {
    val listOfCard = mutableListOf<CardModel>()
    this.forEach {
        listOfCard.add(
            CardModel(
                cardNumber = it.pan.toString(),
                cardType = CardType.MELLAT_CARD,
                amount = it.maxAmount.toDouble(),
                currency = "ریال",
                placeholder = "placeholder",
                expiredDate = it.expireDate.toString()
            )
        )
    }
    return listOfCard
}