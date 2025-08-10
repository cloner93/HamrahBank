package com.pmb.data.repository.favorite

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.domain.model.favorite.FetchFavoriteAccountRequest
import com.pmb.domain.model.favorite.FetchFavoriteAccountResponse
import com.pmb.domain.model.favorite.InsertFavoriteAccountRequest
import com.pmb.domain.model.favorite.RemoveFavoriteAccountRequest
import com.pmb.domain.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider
) : FavoriteRepository {
    override fun fetchFavoriteAccounts(fetchFavoriteAccountRequest: FetchFavoriteAccountRequest): Flow<Result<List<FetchFavoriteAccountResponse>>> {
        return remoteServiceProvider.getFavoriteService()
            .fetchFavoriteAccounts(fetchFavoriteAccountRequest).mapApiResult { it.second }
    }

    override fun insertFavoriteAccount(insertFavoriteAccount: InsertFavoriteAccountRequest): Flow<Result<Boolean>> =
        flow {
            emit(Result.Loading)
            remoteServiceProvider.getFavoriteService().insertFavoriteAccount(insertFavoriteAccount)
                .mapApiResult { result ->
                    if (result.first?.statusMessage == "موفق") {
                        Result.Success(true)
                    } else {
                        Result.Error("Failed to insert favorite account")
                    }
                }
        }

    override fun removeFavoriteAccount(removeFavoriteAccountRequest: RemoveFavoriteAccountRequest): Flow<Result<Boolean>> =
        flow {
            emit(Result.Loading)
            remoteServiceProvider.getFavoriteService().removeFavoriteAccount(removeFavoriteAccountRequest)
                .mapApiResult { result ->
                    if (result.first?.statusMessage == "موفق") {
                        Result.Success(true)
                    } else {
                        Result.Error("Failed to insert favorite account")
                    }
                }
        }
}