package com.pmb.domain.usecae.transactions

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.repository.transactions.TransactionsByCountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionsByCountUsaCase @Inject constructor(
    private val repository: TransactionsByCountRepository
) : BaseUseCase<DepositModel, List<TransactionModel>>() {

    override suspend fun execute(params: DepositModel): Flow<Result<List<TransactionModel>>> {
        return repository.getTransactionsByCount(
            extAccNo = params.depositNumber.toLong(),
            categoryCode = params.categoryCode,
            count = 10,
        )
    }
}