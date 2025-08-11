package com.pmb.domain.usecae.favorite

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.favorite.FetchFavoriteAccountRequest
import com.pmb.domain.model.favorite.FetchFavoriteAccountResponse
import com.pmb.domain.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAllTransferFavoriteAccountsUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<Unit, List<FetchFavoriteAccountResponse>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<FetchFavoriteAccountResponse>>> {
        return favoriteRepository.fetchFavoriteAccounts( type = FetchFavoriteReturnType.All,
            fetchFavoriteAccountRequest = FetchFavoriteAccountRequest(
                favoriteType = FetchFavoriteInputType.Transfer.type, fetchFavoriteMode = true
            )
        )
    }
}

data class FetchFavoriteAccountsParams(
    val favoriteType: Int, val fetchFavoriteMode: Boolean
)

enum class FetchFavoriteInputType(val type:Int){
    Transfer(1)
}
enum class FetchFavoriteReturnType(val type: Int) {
    Favorites(0), Recent(1), All(2)
}