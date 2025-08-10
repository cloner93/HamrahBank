package com.pmb.data.serviceProvider.remote.favorite

import com.pmb.core.platform.Result
import com.pmb.data.model.AnyModel
import com.pmb.domain.model.favorite.FetchFavoriteAccountRequest
import com.pmb.domain.model.favorite.FetchFavoriteAccountResponse
import com.pmb.domain.model.favorite.InsertFavoriteAccountRequest
import com.pmb.domain.model.favorite.RemoveFavoriteAccountRequest
import com.pmb.model.SuccessData
import kotlinx.coroutines.flow.Flow

interface FavoriteService {
    fun fetchFavoriteAccounts(fetchFavoriteAccountRequest: FetchFavoriteAccountRequest): Flow<Result<SuccessData<List<FetchFavoriteAccountResponse>>>>
    fun insertFavoriteAccount(insertFavoriteAccount: InsertFavoriteAccountRequest): Flow<Result<SuccessData<AnyModel>>>
    fun removeFavoriteAccount(removeFavoriteAccountRequest: RemoveFavoriteAccountRequest): Flow<Result<SuccessData<AnyModel>>>

}