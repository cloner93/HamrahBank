package com.pmb.data.repository.transaction

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.mapper.transactionService.mapToDomain
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.TransactionResponse
import com.pmb.domain.repository.transactions.TransactionsByDateRepository
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionsByDateRepositoryImpl @Inject constructor(
    private val client: NetworkManger
) : TransactionsByDateRepository {
    override fun getTransactionsByDate(
        transactionRequest: TransactionRequest
    ): Flow<Result<List<TransactionModel>>> {

        return client.request<TransactionRequest, List<TransactionResponse>>(
            endpoint = "transactions/by-date",
            data = transactionRequest
        ).mapApiResult { it.second.mapToDomain() }
    }
}
