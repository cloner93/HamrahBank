package com.pmb.domain.usecae.favorite

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.favorite.RemoveFavoriteAccountRequest
import com.pmb.domain.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveFavoriteAccountUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<RemoveFavoriteAccountParams, Boolean>() {
    override suspend fun execute(params: RemoveFavoriteAccountParams): Flow<Result<Boolean>> {
        return favoriteRepository.removeFavoriteAccount(
            removeFavoriteAccountRequest = params.toRequest()
        )
    }
}

data class RemoveFavoriteAccountParams(
    val number: String
) {
    fun toRequest(): RemoveFavoriteAccountRequest {
        return RemoveFavoriteAccountRequest(
            number = number,
        )
    }
}