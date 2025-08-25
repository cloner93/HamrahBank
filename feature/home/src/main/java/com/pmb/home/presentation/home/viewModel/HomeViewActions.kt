package com.pmb.home.presentation.home.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface HomeViewActions : BaseViewAction {
    data object ClearAlert : HomeViewActions
    data object GetData : HomeViewActions
    data object ShowGuideBottomSheet : HomeViewActions
    data object CloseGuideBottomSheet : HomeViewActions
}