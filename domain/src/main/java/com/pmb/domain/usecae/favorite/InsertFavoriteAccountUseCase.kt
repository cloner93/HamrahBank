package com.pmb.domain.usecae.favorite

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.favorite.InsertFavoriteAccountRequest
import com.pmb.domain.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertFavoriteAccountUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<InsertFavoriteAccountParams, Boolean>() {
    override suspend fun execute(params: InsertFavoriteAccountParams): Flow<Result<Boolean>> {
        return favoriteRepository.insertFavoriteAccount(
            insertFavoriteAccount = params.toRequest()
        )
    }

}

data class InsertFavoriteAccountParams(
    val ownerDescription: String,
    val number: String
) {
    fun toRequest(): InsertFavoriteAccountRequest {
        return InsertFavoriteAccountRequest(
            ownerDescription = ownerDescription,
            number = number
        )
    }
}