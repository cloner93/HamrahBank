package com.pmb.home.presentation.home.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.home.domain.home.entity.HomeEntity

data class HomeViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val homeData: HomeEntity? = null,
    val isGuideBottomSheetVisible: Boolean = false
) : BaseViewState