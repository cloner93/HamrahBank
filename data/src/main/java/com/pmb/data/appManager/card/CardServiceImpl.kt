package com.pmb.data.appManager.card

import com.pmb.core.platform.Result
import com.pmb.domain.model.Card
import com.pmb.model.SuccessData
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardServiceImpl @Inject constructor(
    private val client: NetworkManger
) : CardService {
    override fun getCardList(): Flow<Result<SuccessData<List<Card>>>> {
        return client.request<Unit, List<Card>>(endpoint = "card/getUserCard")
    }

}