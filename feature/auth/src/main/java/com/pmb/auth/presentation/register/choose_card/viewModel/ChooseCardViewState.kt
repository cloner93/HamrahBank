package com.pmb.auth.presentation.register.choose_card.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.openAccount.CardFormatModel
import com.pmb.domain.model.openAccount.FetchCardFormatResponse

data class ChooseCardViewState(
    val isLoading: Boolean = false,
    val horizontalCardList: List<CardFormatModel> = emptyList(),
    val verticalCardList: List<CardFormatModel> = emptyList(),
    val selectedCard: CardFormatModel? = null,
    val alertModelState: AlertModelState?=null
) : BaseViewState
