package com.pmb.account.usecase.deposits

import com.pmb.account.presentation.component.TransactionModel
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
                transactionsRepository.getTransactionsForDeposit(depositId)
            } catch (e: Exception) {
                throw e
            }
        }
}
