package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountSearchUseCase @Inject constructor(
    private val repository: TransferRepository
) : BaseUseCase<AccountSearchUseCase.Params, List<TransactionClientBankEntity>>() {
    override suspend fun execute(params: Params): Flow<Result<List<TransactionClientBankEntity>>> {
        return repository.searchAccounts(params.value)
    }

    data class Params(val value: String)
}