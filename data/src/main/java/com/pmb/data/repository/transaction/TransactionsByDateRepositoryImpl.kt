package com.pmb.data.repository.transaction

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.TransactionResponse
import com.pmb.domain.model.TransactionType
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

private fun List<TransactionResponse>.mapToDomain(): List<TransactionModel> {
    val listOfTransaction = mutableListOf<TransactionModel>()
    this.forEach { it ->
        var amount: Long = 0
        var type = TransactionType.UNKNOWN

        if (it.creditAmount != 0.toLong()) {
            amount = it.creditAmount
            type = TransactionType.RECEIVE
        } else if (it.debitAmount != 0.toLong()) {
            amount = it.debitAmount
            type = TransactionType.TRANSFER
        }

        listOfTransaction.add(
            TransactionModel(
                transactionId = it.transactionNumber.toString(),
                type = type,
                amount = amount.toDouble(),
                currency = "ریال",
                date = it.transactionDate.toString()
            )
        )

    }
    return listOfTransaction
}