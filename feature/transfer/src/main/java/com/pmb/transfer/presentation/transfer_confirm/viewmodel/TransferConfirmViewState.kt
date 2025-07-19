package com.pmb.transfer.presentation.transfer_confirm.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.PaymentType
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.entity.TransferSourceEntity

data class TransferConfirmViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val defaultSource: TransferSourceEntity? = null,
    val sourceCardBanks: List<CardBankEntity> = mutableListOf(),
    val sourceAccountBanks: List<AccountBankEntity> = mutableListOf(),
    val defaultReason: ReasonEntity? = null,
    val depositId: String = "",
    val favoriteDestination: Boolean = false,
    val destinationAccount: TransactionClientBankEntity? = null,
    val destinationAmount: Double = 0.0,
    val transferMethod: TransferMethodEntity? = null
) : BaseViewState {
    val enableConfirmButton: Boolean
        get() = when (transferMethod?.paymentType) {
            PaymentType.CARD_TO_CARD -> defaultSource is TransferSourceEntity.Card

            PaymentType.INTERNAL_SATNA,
            PaymentType.INTERNAL_PAYA,
            PaymentType.INTERNAL_BRIDGE -> defaultSource is TransferSourceEntity.Account
                    && defaultReason != null

            PaymentType.MELLAT_TO_MELLAT -> defaultSource != null

            null -> false
        } && !loading
}