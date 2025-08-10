package com.pmb.auth.presentation.register.choose_card.viewModel

import com.pmb.core.platform.BaseViewAction
import com.pmb.domain.model.openAccount.CardFormatModel
import com.pmb.domain.model.openAccount.FetchCardFormatResponse

sealed interface ChooseCardViewActions : BaseViewAction{
    data object GetCardFormatList: ChooseCardViewActions
    data class SelectCardFormat(val card : CardFormatModel): ChooseCardViewActions
}