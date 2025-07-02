package com.pmb.data.repository.card

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.Card
import com.pmb.domain.model.CardModel
import com.pmb.domain.model.CardType
import com.pmb.domain.repository.card.CardListRepository
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardLIstRepositoryImpl @Inject constructor(
    private val client: NetworkManger
) : CardListRepository {
    override fun getCardList(): Flow<Result<List<CardModel>>> {
        return client.request<Unit, List<Card>>(endpoint = "card/getUserCard")
            .mapApiResult {
                it.second.toDomain()
            }
    }
}

private fun List<Card>.toDomain(): List<CardModel> {
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