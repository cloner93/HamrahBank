package com.pmb.transfer.presentation.transfer_confirm.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.ReasonEntity

data class TransferConfirmViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val sourceCardBanks: List<CardBankEntity> = mutableListOf(),
    val sourceAccountBanks: List<AccountBankEntity> = mutableListOf(),
    val defaultCardBank: CardBankEntity? = null,
    val defaultAccountBank: AccountBankEntity? = null,
    val defaultReason: ReasonEntity? = null,
    val depositId: String = "",
    val favoriteDestination: Boolean = false,
) : BaseViewState