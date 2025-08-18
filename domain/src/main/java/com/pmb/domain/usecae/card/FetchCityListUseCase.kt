package com.pmb.domain.usecae.card

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.card.City
import com.pmb.domain.repository.card.CardsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class FetchCityListUseCase @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseUseCase<FetchCityListParams, List<City>>() {
    override suspend fun execute(params: FetchCityListParams): Flow<Result<List<City>>> {
        return cardsRepository.getCityList(params)
    }
}

data class FetchCityListParams(
    val flag: Int = 1,
    val provinceCode: Int,
)