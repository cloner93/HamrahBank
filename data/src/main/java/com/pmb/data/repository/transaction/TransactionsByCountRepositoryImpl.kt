package com.pmb.data.repository.transaction

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.mapper.transactionService.mapToDomain
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.TransactionResponse
import com.pmb.domain.repository.transactions.TransactionsByCountRepository
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionsByCountRepositoryImpl @Inject constructor(
    private val client: NetworkManger
) : TransactionsByCountRepository {

    override fun getTransactionsByCount(
        transactionRequest: TransactionRequest
    ): Flow<Result<List<TransactionModel>>> {
        return client.request<TransactionRequest, List<TransactionResponse>>(
            endpoint = "transactions/count",
            data = transactionRequest
        ).mapApiResult { it.second.mapToDomain() }
    }
}