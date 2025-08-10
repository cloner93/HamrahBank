package com.pmb.data.repository.favorite

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.domain.model.favorite.FetchFavoriteAccountRequest
import com.pmb.domain.model.favorite.FetchFavoriteAccountResponse
import com.pmb.domain.model.favorite.InsertFavoriteAccountRequest
import com.pmb.domain.model.favorite.RemoveFavoriteAccountRequest
import com.pmb.domain.repository.favorite.FavoriteRepository
import com.pmb.domain.usecae.favorite.FetchFavoriteReturnType
import com.pmb.domain.usecae.favorite.FetchFavoriteReturnType.Favorites
import com.pmb.domain.usecae.favorite.FetchFavoriteReturnType.Recent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider
) : FavoriteRepository {
    override fun fetchFavoriteAccounts(
        type: FetchFavoriteReturnType, fetchFavoriteAccountRequest: FetchFavoriteAccountRequest
    ): Flow<Result<List<FetchFavoriteAccountResponse>>> {
        return remoteServiceProvider.getFavoriteService()
            .fetchFavoriteAccounts(fetchFavoriteAccountRequest).mapApiResult {
                when (type) {
                    Favorites-> it.second.filter { item -> item.type == type.type }
                    Recent -> it.second.filter { item -> item.type == type.type }
                    else -> it.second
                }
            }
    }

    override fun insertFavoriteAccount(insertFavoriteAccount: InsertFavoriteAccountRequest): Flow<Result<Unit>> {
        return remoteServiceProvider.getFavoriteService().insertFavoriteAccount(insertFavoriteAccount)
            .mapApiResult { it.second }
    }

    override fun removeFavoriteAccount(removeFavoriteAccountRequest: RemoveFavoriteAccountRequest): Flow<Result<Unit>> {
        return remoteServiceProvider.getFavoriteService().removeFavoriteAccount(removeFavoriteAccountRequest)
            .mapApiResult { it.second }
    }
}