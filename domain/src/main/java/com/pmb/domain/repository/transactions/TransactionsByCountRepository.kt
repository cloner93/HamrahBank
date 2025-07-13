package com.pmb.domain.repository.transactions

import com.pmb.core.platform.Result
import com.pmb.domain.model.TransactionModel
import kotlinx.coroutines.flow.Flow

interface TransactionsByCountRepository {
    fun getTransactionsByCount(
        extAccNo: Long,
        categoryCode: Long,
        count: Int
    ): Flow<Result<List<TransactionModel>>>
}
