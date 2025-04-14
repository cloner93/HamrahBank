package com.pmb.account.usecase.deposits

import com.pmb.account.presentation.component.TransactionModel
import com.pmb.account.tempRepo.TransactionsRepository
import com.pmb.core.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionSearchUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(depositId: String, query: String): List<TransactionModel> =
        withContext(coroutineDispatcher) {
            try {
                transactionsRepository.searchTransactionsInDeposit(depositId, query)
            } catch (e: Exception) {
                throw e
            }
        }
}