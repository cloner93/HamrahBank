package com.pmb.domain.repository.favorite

import com.pmb.core.platform.Result
import com.pmb.domain.model.favorite.FetchFavoriteAccountRequest
import com.pmb.domain.model.favorite.FetchFavoriteAccountResponse
import com.pmb.domain.model.favorite.InsertFavoriteAccountRequest
import com.pmb.domain.model.favorite.RemoveFavoriteAccountRequest
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun fetchFavoriteAccounts(fetchFavoriteAccountRequest: FetchFavoriteAccountRequest): Flow<Result<List<FetchFavoriteAccountResponse>>>
    fun insertFavoriteAccount(insertFavoriteAccount: InsertFavoriteAccountRequest): Flow<Result<Boolean>>
    fun removeFavoriteAccount(removeFavoriteAccountRequest: RemoveFavoriteAccountRequest): Flow<Result<Boolean>>
}