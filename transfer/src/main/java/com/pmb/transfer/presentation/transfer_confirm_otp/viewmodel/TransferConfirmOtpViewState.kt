package com.pmb.transfer.presentation.transfer_confirm_otp.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.CardBankEntity

data class TransferConfirmOtpViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val cardBank: CardBankEntity? = null,
    val cvv2: String = "",
    val password: String = "",
    val enableResend: Boolean = true,
    val timer: Int = 0,
) : BaseViewState {
    val enableButton: Boolean
        get() = password.length >= 4 && cvv2.length in 3..4
}

