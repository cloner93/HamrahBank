package com.pmb.account.usecase.deposits

import com.pmb.account.tempRepo.TransactionsRepository
import com.pmb.core.qualifier.IoDispatcher
import com.pmb.domain.model.TransactionModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(depositId: String): List<TransactionModel> =
        withContext(coroutineDispatcher) {
            try {
                transactionsRepository.getTransactionsForDeposit(depositId)
            } catch (e: Exception) {
                throw e
            }
        }
}
