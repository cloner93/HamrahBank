package com.pmb.transfer.presentation.transfer_confirm_otp.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.TransferConfirmEntity

sealed interface TransferConfirmOtpViewActions : BaseViewAction {
    data object ClearAlert : TransferConfirmOtpViewActions
    data object SubmitOtp : TransferConfirmOtpViewActions
    data object ResendOtp : TransferConfirmOtpViewActions
    data class UpdateCardBank(val cardBank: CardBankEntity) : TransferConfirmOtpViewActions
    data class TransferConfirm(val transferConfirm: TransferConfirmEntity) :
        TransferConfirmOtpViewActions

    data class UpdateDyPassword(val dyPassword: String) : TransferConfirmOtpViewActions
    data class UpdateCvv2(val cvv2: String) : TransferConfirmOtpViewActions
    data object StartTimer : TransferConfirmOtpViewActions
}