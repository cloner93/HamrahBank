package com.pmb.domain.usecae.favorite

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.favorite.FetchFavoriteAccountRequest
import com.pmb.domain.model.favorite.FetchFavoriteAccountResponse
import com.pmb.domain.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchFavoriteAccountsUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<FetchFavoriteAccountsParams,List<FetchFavoriteAccountResponse>>() {
    override suspend fun execute(params: FetchFavoriteAccountsParams): Flow<Result<List<FetchFavoriteAccountResponse>>> {
        return favoriteRepository.fetchFavoriteAccounts(
            fetchFavoriteAccountRequest = FetchFavoriteAccountRequest(
                favoriteType = params.favoriteType,
                fetchFavoriteMode = params.fetchFavoriteMode
            )
        )
    }
}

data class FetchFavoriteAccountsParams (
    val favoriteType: Int,
    val fetchFavoriteMode: Boolean
)