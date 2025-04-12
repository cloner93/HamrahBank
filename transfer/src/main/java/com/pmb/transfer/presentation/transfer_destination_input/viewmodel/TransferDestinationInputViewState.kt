package com.pmb.transfer.presentation.transfer_destination_input.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

data class TransferDestinationInputViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val accountDetail: TransactionClientBankEntity? = null,
) : BaseViewState