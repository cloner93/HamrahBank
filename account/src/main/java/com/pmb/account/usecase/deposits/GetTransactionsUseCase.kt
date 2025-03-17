package com.pmb.account.usecase.deposits

import com.pmb.account.presentation.component.TransactionModel
import com.pmb.account.presentation.component.TransactionType
import com.pmb.account.tempRepo.TransactionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) {
    suspend operator fun invoke(depositId: String): List<TransactionModel> =
        withContext(Dispatchers.IO) {
            try {
                val transactions = transactionsRepository.getTransactionsForDeposit(depositId)

                transactions.map { transaction ->
                    TransactionModel(
                        amount = transaction.amount,
                        date = transaction.date,
                        type = mapTransactionType(transaction.type.toString()),
                        title = transaction.title,
                        currency = transaction.currency,
                    )
                }
            } catch (e: Exception) {
                throw e
            }
        }

    private fun mapTransactionType(type: String): TransactionType {
        return when (type.uppercase()) {
            "DEPOSIT" -> TransactionType.DEPOSIT
            "WITHDRAWAL" -> TransactionType.WITHDRAWAL
            "TRANSFER" -> TransactionType.TRANSFER
            "FEE" -> TransactionType.FEE
            else -> TransactionType.TRANSFER // Default case
        }
    }
}
