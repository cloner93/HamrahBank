package com.pmb.auth.presentation.scan_card_info.card_info.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface CardInfoViewEvents : BaseViewEvent {
    data object SendCardInfoSucceed : CardInfoViewEvents
}