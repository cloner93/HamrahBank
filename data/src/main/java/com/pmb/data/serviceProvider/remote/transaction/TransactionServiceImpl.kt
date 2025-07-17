package com.pmb.data.serviceProvider.remote.transaction

import com.pmb.core.platform.Result
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.TransactionResponse
import com.pmb.model.SuccessData
import com.pmb.network.NetworkManger
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class TransactionServiceImpl @Inject constructor(
    private val client: NetworkManger
) : TransactionService {
    override fun getTransactionPaging(transactionRequest: TransactionRequest): Flow<Result<SuccessData<List<TransactionResponse>>>> {
        return client.request<TransactionRequest, List<TransactionResponse>>(
            endpoint = "transactions/count",
            data = transactionRequest
        )
    }

    override fun getTransactionByCount(transactionRequest: TransactionRequest?): Flow<Result<SuccessData<List<TransactionResponse>>>> {
        return client.request<TransactionRequest, List<TransactionResponse>>(
            endpoint = "transactions/count",
            data = transactionRequest
        )
    }

    override fun getTransactionByDate(transactionRequest: TransactionRequest?): Flow<Result<SuccessData<List<TransactionResponse>>>> {
        return client.request<TransactionRequest, List<TransactionResponse>>(
            endpoint = "transactions/by-date",
            data = transactionRequest
        )
    }
}