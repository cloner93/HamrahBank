package com.pmb.transfer.presentation.transfer_confirm.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.entity.TransferReasonEntity

sealed interface TransferConfirmViewActions : BaseViewAction {
    data object ClearAlert : TransferConfirmViewActions
    data class SubmitTransferData(
        val transferMethod: TransferMethodEntity,
        val account: TransactionClientBankEntity,
        val amount: Long,
        val makeFavoriteDestination: Boolean,
        val depositId: String? = null,
        val transferReason: TransferReasonEntity? = null,
    ) : TransferConfirmViewActions
}