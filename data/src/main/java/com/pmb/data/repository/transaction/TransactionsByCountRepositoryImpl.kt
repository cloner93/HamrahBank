package com.pmb.data.repository.transaction

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.TransactionResponse
import com.pmb.domain.model.TransactionType
import com.pmb.domain.repository.transactions.TransactionsByCountRepository
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionsByCountRepositoryImpl @Inject constructor(
    private val client: NetworkManger
) : TransactionsByCountRepository {
    override fun getTransactionsByCount(
        extAccNo: Long,
        categoryCode: Long,
        count: Int
    ): Flow<Result<List<TransactionModel>>> {
        val transactionRequest =
            TransactionRequest(extAccNo = extAccNo, count = count, categoryCode = categoryCode)

        return client.request<TransactionRequest, List<TransactionResponse>>(
            endpoint = "transactions/count",
            data = transactionRequest
        ).mapApiResult { it.second.mapToDomain() }
    }
}

private fun List<TransactionResponse>.mapToDomain(): List<TransactionModel> {
    val listOfTransaction = mutableListOf<TransactionModel>()
    this.forEach { it ->
        listOfTransaction.add(
            TransactionModel(
                transactionId = it.transactionNumber.toString(),
                type = TransactionType.UNKNOWN,
                amount = it.balance.toDouble(),
                currency = "ریال",
                date = it.transactionDate.toString()
            )
        )

    }
    return listOfTransaction
}