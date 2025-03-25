package com.pmb.auth.presentation.scan_card_info.card_confirm.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface CardInformationConfirmViewEvents : BaseViewEvent {
    data object SendCardInformationConfirmation : CardInformationConfirmViewEvents
}