package com.pmb.auth.presentation.scan_card_info.card_confirm.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface CardInformationConfirmViewActions : BaseViewAction {
    data object ClearAlert : CardInformationConfirmViewActions
    data object GetCardInformation : CardInformationConfirmViewActions
    data object SendCardInformationConfirmation : CardInformationConfirmViewActions
}