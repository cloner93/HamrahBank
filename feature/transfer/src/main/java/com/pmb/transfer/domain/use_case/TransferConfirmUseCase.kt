package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.PaymentType
import com.pmb.transfer.domain.entity.TransferConfirmEntity
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransferConfirmUseCase @Inject constructor(
    private val repository: TransferRepository
) : BaseUseCase<TransferConfirmUseCase.Params, TransferConfirmEntity>() {
    override suspend fun execute(params: Params): Flow<Result<TransferConfirmEntity>> {
        return repository.transferConfirm(params)
    }

    data class Params(
        val sourceNumber: String,
        val destinationNumber: String,
        val customerId: String,
        val amount: Double,
        val reasonId: Long?,
        val depositId: String,
        val transferMethod: PaymentType,
        val favoriteDestination: Boolean
    )
}