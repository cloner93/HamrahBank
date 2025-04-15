package com.pmb.transfer.presentation.transfer_confirm_otp.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.TransferReceiptEntity

sealed interface TransferConfirmOtpViewEvents : BaseViewEvent {
    data class NavigateToReceipt(val receipt: TransferReceiptEntity) : TransferConfirmOtpViewEvents
}