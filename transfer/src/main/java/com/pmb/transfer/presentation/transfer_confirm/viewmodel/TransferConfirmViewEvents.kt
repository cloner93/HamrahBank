package com.pmb.transfer.presentation.transfer_confirm.viewmodel

import com.pmb.core.platform.BaseViewEvent

sealed interface TransferConfirmViewEvents : BaseViewEvent {
    data class NavigateToOtp(val id: String, val duration: Int) : TransferConfirmViewEvents
}