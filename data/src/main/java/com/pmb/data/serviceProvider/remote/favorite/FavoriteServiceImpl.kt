package com.pmb.data.serviceProvider.remote.favorite

import com.pmb.core.platform.Result
import com.pmb.domain.model.favorite.FetchFavoriteAccountRequest
import com.pmb.domain.model.favorite.FetchFavoriteAccountResponse
import com.pmb.domain.model.favorite.InsertFavoriteAccountRequest
import com.pmb.domain.model.favorite.RemoveFavoriteAccountRequest
import com.pmb.model.SuccessData
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteServiceImpl @Inject constructor(
    private val client: NetworkManger,
) : FavoriteService {
    override fun fetchFavoriteAccounts(fetchFavoriteAccountRequest: FetchFavoriteAccountRequest): Flow<Result<SuccessData<List<FetchFavoriteAccountResponse>>>> {
        return client.request<FetchFavoriteAccountRequest, List<FetchFavoriteAccountResponse>>(
            endpoint = "favorite/fetch_favorite", data = fetchFavoriteAccountRequest
        )
    }

    override fun insertFavoriteAccount(insertFavoriteAccount: InsertFavoriteAccountRequest): Flow<Result<SuccessData<Any>>> {
        return client.request<InsertFavoriteAccountRequest, Any>(
            endpoint = "favorite/insert_favorite", data = insertFavoriteAccount
        )
    }

    override fun removeFavoriteAccount(removeFavoriteAccountRequest: RemoveFavoriteAccountRequest): Flow<Result<SuccessData<Any>>> {
        return client.request<RemoveFavoriteAccountRequest, Any>(
            endpoint = "favorite/remove_favorite", data = removeFavoriteAccountRequest
        )
    }

}