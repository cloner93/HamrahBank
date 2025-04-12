package com.pmb.transfer.presentation.transfer_edit_destination.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

data class TransferEditDestinationViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val transactionClientBanks: List<TransactionClientBankEntity> = emptyList(),
): BaseViewState