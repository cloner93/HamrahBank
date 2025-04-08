package com.pmb.account.usecase.deposits

import com.pmb.account.presentation.component.DepositModel
import com.pmb.account.tempRepo.DepositsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
TODO checkList GetDepositsUseCase.kt
 *
 * - add @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
 */

class GetDepositsUseCase @Inject constructor(
    private val depositsRepository: DepositsRepository
) {
    suspend operator fun invoke(): List<DepositModel> = withContext(Dispatchers.IO) {
        try {
            val deposits = depositsRepository.getDeposits()

            deposits.map { deposit ->
                DepositModel(
                    title = deposit.title,
                    desc = deposit.desc,
                    depositNumber = deposit.depositNumber,
                    amount = deposit.amount,
                    currency = deposit.currency,
                    ibanNumber = deposit.ibanNumber,
                    cardNumber = deposit.cardNumber,
                )
            }
        } catch (e: Exception) {
            throw e
        }
    }
}