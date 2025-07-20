package com.pmb.data.serviceProvider.remote.transaction

import com.pmb.core.platform.Result
import com.pmb.domain.model.SummarizeResponse
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.TransactionResponse
import com.pmb.model.SuccessData
import kotlinx.coroutines.flow.Flow

interface TransactionService {
    fun getTransactionPaging(transactionRequest: TransactionRequest)
            : Flow<Result<SuccessData<List<TransactionResponse>>>>

    fun getTransactionByCount(transactionRequest: TransactionRequest?): Flow<Result<SuccessData<List<TransactionResponse>>>>
    fun getTransactionByDate(transactionRequest: TransactionRequest?): Flow<Result<SuccessData<List<TransactionResponse>>>>
    fun getSummarize(transactionRequest: TransactionRequest?): Flow<Result<SuccessData<List<SummarizeResponse>>>>
}
