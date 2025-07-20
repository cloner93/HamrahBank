package com.pmb.data.serviceProvider.remote.transaction

import com.pmb.core.platform.Result
import com.pmb.domain.model.SummarizeResponse
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.TransactionResponse
import com.pmb.model.SuccessData
import com.pmb.network.NetworkManger
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class TransactionServiceImpl @Inject constructor(
    private val client: NetworkManger
) : TransactionService {
    override fun getTransactionByCountPaging(transactionRequest: TransactionRequest): Flow<Result<SuccessData<List<TransactionResponse>>>> {
        return client.request<TransactionRequest, List<TransactionResponse>>(
            endpoint = "transactions/count",
            data = transactionRequest
        )
    }

    override fun getTransactionByDatePaging(transactionRequest: TransactionRequest?): Flow<Result<SuccessData<List<TransactionResponse>>>> {
        return client.request<TransactionRequest, List<TransactionResponse>>(
            endpoint = "transactions/by-date",
            data = transactionRequest
        )
    }

    override fun getSummarize(transactionRequest: TransactionRequest?): Flow<Result<SuccessData<List<SummarizeResponse>>>> {
        return client.request<TransactionRequest, List<SummarizeResponse>>(
            endpoint = "transactions/summarize",
            data = transactionRequest
        )
    }
}