package com.pmb.domain.usecae.favorite

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.favorite.FetchFavoriteAccountRequest
import com.pmb.domain.model.favorite.FetchFavoriteAccountResponse
import com.pmb.domain.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchRecentTransferFavoriteAccountsUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<Unit, List<FetchFavoriteAccountResponse>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<FetchFavoriteAccountResponse>>> {
        return favoriteRepository.fetchFavoriteAccounts(
            type = FetchFavoriteReturnType.Recent,
            fetchFavoriteAccountRequest = FetchFavoriteAccountRequest(
                favoriteType = FetchFavoriteInputType.Transfer.type, fetchFavoriteMode = true
            )
        )
    }
}

