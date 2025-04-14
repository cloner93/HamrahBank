package com.pmb.account.usecase.deposits

import com.pmb.account.presentation.component.DepositModel
import com.pmb.account.tempRepo.DepositsRepository
import com.pmb.core.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDepositsUseCase @Inject constructor(
    private val depositsRepository: DepositsRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): List<DepositModel> = withContext(coroutineDispatcher) {
        try {
            depositsRepository.getDeposits()
        } catch (e: Exception) {
            throw e
        }
    }
}