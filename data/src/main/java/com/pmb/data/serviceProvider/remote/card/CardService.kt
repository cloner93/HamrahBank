package com.pmb.data.serviceProvider.remote.card

import com.pmb.core.platform.Result
import com.pmb.domain.model.Card
import com.pmb.model.SuccessData
import kotlinx.coroutines.flow.Flow

interface CardService {
    fun getCardList() : Flow<Result<SuccessData<List<Card>>>>
}