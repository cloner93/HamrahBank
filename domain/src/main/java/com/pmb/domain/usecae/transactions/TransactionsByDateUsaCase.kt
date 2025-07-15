package com.pmb.domain.usecae.transactions

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.repository.transactions.TransactionsByDateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionsByDateUsaCase @Inject constructor(
    private val repository: TransactionsByDateRepository
) : BaseUseCase<TransactionRequest, List<TransactionModel>>() {

    override suspend fun execute(params: TransactionRequest): Flow<Result<List<TransactionModel>>> {
        return repository.getTransactionsByDate(
            params
        )
    }
}