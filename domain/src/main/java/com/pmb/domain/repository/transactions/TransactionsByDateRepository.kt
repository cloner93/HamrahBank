package com.pmb.domain.repository.transactions

import com.pmb.core.platform.Result
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import kotlinx.coroutines.flow.Flow

interface TransactionsByDateRepository {
    fun getTransactionsByDate(
        transactionRequest: TransactionRequest
    ): Flow<Result<List<TransactionModel>>>
}
