package com.pmb.transfer.presentation.transfer_confirm.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity

sealed interface TransferConfirmViewActions : BaseViewAction {
    data object ClearAlert : TransferConfirmViewActions
    data object SubmitTransferData : TransferConfirmViewActions

    data class SelectCardBank(val item: CardBankEntity) : TransferConfirmViewActions
    data class UpdateData(
        val account: TransactionClientBankEntity?,
        val amount: Double?,
        val transferMethod: TransferMethodEntity?,
        val reason: ReasonEntity?
    ) : TransferConfirmViewActions

    data class SelectAccountBank(val item: AccountBankEntity) : TransferConfirmViewActions
    data class UpdateFavoriteDestination(val favorite: Boolean) : TransferConfirmViewActions
    data class UpdateDepositId(val depositId: String) : TransferConfirmViewActions
    data class UpdateDescription(val description: String) : TransferConfirmViewActions
    data object SelectTransferReason : TransferConfirmViewActions
}