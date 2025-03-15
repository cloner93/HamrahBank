package com.pmb.auth.presentation.scan_card_info.card_confirm.viewModel

import com.pmb.auth.domain.scan_card_info.card_confirmation.entity.CardInformationConfirmationEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class CardInformationConfirmViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val data: CardInformationConfirmationEntity? = null
) : BaseViewState
