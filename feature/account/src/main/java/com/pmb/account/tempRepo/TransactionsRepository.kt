package com.pmb.account.tempRepo

import com.pmb.domain.model.TransactionModel
import javax.inject.Inject

class TransactionsRepository @Inject constructor() {
    suspend fun getTransactionsForDeposit(depositId: String): List<TransactionModel> {
        return listOf()
    }

    suspend fun searchTransactionsInDeposit(
        depositId: String,
        query: String
    ): List<TransactionModel> {
        // FIXME: edit it
        return getTransactionsForDeposit(depositId).filter { transaction ->
            transaction.title.contains(query, ignoreCase = true) ||
                    transaction.amount.toString().contains(query, ignoreCase = true) ||
                    transaction.date.contains(query, ignoreCase = true)
        }
    }

    suspend fun getTransactionById(depositId: String?, transactionId: String?): TransactionModel? {
        if (depositId == null) return null
        if (transactionId == null) return null
        return getTransactionsForDeposit(depositId)
            .find { it.transactionId == transactionId }
    }
}
