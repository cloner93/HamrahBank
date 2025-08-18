package com.pmb.domain.usecae.card

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.accountType.Province
import com.pmb.domain.repository.card.CardsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class FetchProvinceListUseCase @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseUseCase<Unit, List<Province>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<Province>>> {
        return cardsRepository.getProvinceList()
    }
}