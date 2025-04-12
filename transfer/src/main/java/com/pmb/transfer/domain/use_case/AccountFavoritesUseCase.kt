package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.ClientBankEntity
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountFavoritesUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) : BaseUseCase<Unit, List<TransactionClientBankEntity>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<TransactionClientBankEntity>>> {
        return transferRepository.fetchAccountFavorite()
    }

    override suspend operator fun invoke(): Flow<Result<List<TransactionClientBankEntity>>> = invoke(Unit)
}