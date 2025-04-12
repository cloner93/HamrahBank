package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.param.AccountDetailParam
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountDetailUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) : BaseUseCase<AccountDetailParam, TransactionClientBankEntity>() {
    override suspend fun execute(params: AccountDetailParam): Flow<Result<TransactionClientBankEntity>> {
        return transferRepository.fetchAccountDetail(params)
    }
}