package com.pmb.transfer.presentation.transfer_confirm.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.TransferConfirmEntity

sealed interface TransferConfirmViewEvents : BaseViewEvent {
    data class NavigateToOtp(
        val sourceCardBank: CardBankEntity?,
        val sourceAccountBank: AccountBankEntity?,
        val transferConfirm: TransferConfirmEntity,
    ) : TransferConfirmViewEvents
}