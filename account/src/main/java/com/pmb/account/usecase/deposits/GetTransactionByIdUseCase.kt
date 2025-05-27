package com.pmb.account.usecase.deposits

import com.pmb.account.presentation.component.TransactionModel
import com.pmb.account.tempRepo.TransactionsRepository
import javax.inject.Inject

class GetTransactionByIdUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
) {
    suspend operator fun invoke(depositId: String?, transactionId: String?): TransactionModel? =
        transactionsRepository.getTransactionById(depositId, transactionId)
}