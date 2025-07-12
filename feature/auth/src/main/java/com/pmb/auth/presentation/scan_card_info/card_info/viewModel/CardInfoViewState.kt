package com.pmb.auth.presentation.scan_card_info.card_info.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class CardInfoViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null
) : BaseViewState