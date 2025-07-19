package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.param.AccountRemoveFavoriteParam
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRemoveFavoriteUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) : BaseUseCase<AccountRemoveFavoriteParam, List<TransactionClientBankEntity>>() {
    override suspend fun execute(params: AccountRemoveFavoriteParam): Flow<Result<List<TransactionClientBankEntity>>> {
        return transferRepository.removeAccountFavorite(params)
    }
}