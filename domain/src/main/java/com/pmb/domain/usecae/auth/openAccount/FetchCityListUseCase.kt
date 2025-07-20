package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.cityName.FetchCityListResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCityListUseCase @Inject constructor(
    val authRepository: AuthRepository
) : BaseUseCase<FetchCityListParams, FetchCityListResponse>() {
    override suspend fun execute(params: FetchCityListParams): Flow<Result<FetchCityListResponse>> {
        return authRepository.fetchCityList(stateCode = params.stateCode)
    }
}

data class FetchCityListParams(
    val stateCode: Int
)