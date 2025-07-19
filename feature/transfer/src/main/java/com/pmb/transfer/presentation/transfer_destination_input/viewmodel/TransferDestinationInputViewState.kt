package com.pmb.transfer.presentation.transfer_destination_input.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.utils.BankUtil.isValidIranBankIdentifier

data class TransferDestinationInputViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val accountDetail: TransactionClientBankEntity? = null,
    val identifierNumber: String = "",
    val identifierNumberClipboard: String = "",
    var identifierNumberMaxLength: Int = 24,
    val clipboardTextType: BankIdentifierNumberType? = null,
) : BaseViewState {
    val enableButton: Boolean
        get() = identifierNumber.isValidIranBankIdentifier { maxLength ->
            identifierNumberMaxLength = maxLength
        }
    val showAccount: Boolean
        get() = accountDetail != null
    val showFavorite: Boolean
        get() = !showClipboard
    val showClipboard: Boolean
        get() = identifierNumberClipboard.isValidIranBankIdentifier() && accountDetail == null
}