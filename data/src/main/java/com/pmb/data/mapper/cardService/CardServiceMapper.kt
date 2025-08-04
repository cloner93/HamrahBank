package com.pmb.data.mapper.cardService

import com.pmb.calender.longToString
import com.pmb.domain.model.Card
import com.pmb.domain.model.CardModel
import com.pmb.domain.model.CardType

fun List<Card>?.toDomain(): List<CardModel> {
    val listOfCard = mutableListOf<CardModel>()
    if (this == null)
        return listOfCard

    this.forEach {
        val cardType: CardType = when (it.cardType.toInt()) {
            7 -> CardType.MELLAT_CARD
            4 -> CardType.BON_CARD
            else -> CardType.SPORT_BON_CARD
        }
        val expireDate = with(it.expireDate.longToString()) {
            this?.let {
                this.first.drop(2) + "/" + this.second
            } ?: run {
                "00/00"
            }
        }
        val organization =
            if (it.ownerName.isNullOrBlank()) null else it.ownerName

        listOfCard.add(

            CardModel(
                cardNumber = it.pan.toString(),
                cardType = cardType,
                amount = it.balance.toDouble(),
                cardStatus = it.cardStatus.toInt(),
                currency = "ریال",
                placeholder = organization,
                expiredDate = expireDate
            )
        )
    }
    return listOfCard
}