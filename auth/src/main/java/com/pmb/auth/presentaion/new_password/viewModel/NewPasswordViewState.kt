package com.pmb.auth.presentaion.new_password.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class NewPasswordViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val isShowBottomSheet: Boolean = false
) : BaseViewState