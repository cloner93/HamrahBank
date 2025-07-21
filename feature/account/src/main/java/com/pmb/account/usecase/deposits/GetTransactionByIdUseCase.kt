package com.pmb.account.usecase.deposits

import com.pmb.account.tempRepo.TransactionsRepository
import com.pmb.domain.model.TransactionModel
import javax.inject.Inject

class GetTransactionByIdUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
) {
    suspend operator fun invoke(depositId: String?, transactionId: String?): TransactionModel? =
        transactionsRepository.getTransactionById(depositId, transactionId)
}