package com.pmb.home.domain.home.useCase

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.home.domain.home.entity.HomeEntity
import com.pmb.home.domain.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseUseCase<Unit, HomeEntity>() {
    override suspend fun execute(params: Unit): Flow<Result<HomeEntity>> {
        return homeRepository.getHomeData()
    }

}