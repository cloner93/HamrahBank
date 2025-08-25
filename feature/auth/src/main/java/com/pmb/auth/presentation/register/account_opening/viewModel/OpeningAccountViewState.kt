package com.pmb.auth.presentation.register.account_opening.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class OpeningAccountViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val phoneNumber: String? = null,
    val nationalId: String? = null,
    val birthDateYear: String? = null,
    val birthDateMonth: String? = null,
    val birthDateDay: String? = null,
    val isShowingBottomSheet: Boolean = false,
) : BaseViewState