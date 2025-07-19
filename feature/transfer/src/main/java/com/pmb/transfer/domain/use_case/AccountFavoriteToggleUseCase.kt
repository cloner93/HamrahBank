package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.param.AccountFavoriteToggleParam
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountFavoriteToggleUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) : BaseUseCase<AccountFavoriteToggleParam, List<TransactionClientBankEntity>>() {
    override suspend fun execute(params: AccountFavoriteToggleParam): Flow<Result<List<TransactionClientBankEntity>>> {
        return transferRepository.toggleAccountFavorite(params)
    }
}