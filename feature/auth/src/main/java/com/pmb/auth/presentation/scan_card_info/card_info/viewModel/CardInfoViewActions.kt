package com.pmb.auth.presentation.scan_card_info.card_info.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface CardInfoViewActions : BaseViewAction {
    data object ClearAlert : CardInfoViewActions
    data class SendCardInfo(
        val cardNumber: String,
        val cvv2: String,
        val year: String,
        val month: String,
        val password: String
    ) : CardInfoViewActions
}