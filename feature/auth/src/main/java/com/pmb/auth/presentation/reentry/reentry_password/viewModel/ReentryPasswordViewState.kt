package com.pmb.auth.presentation.reentry.reentry_password.viewModel

import com.pmb.ballon.models.AccountSampleModel
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class ReentryPasswordViewState(
    val isLoading:Boolean=false,
    val alertModelState: AlertModelState?=null,
    val userData : AccountSampleModel?=null,
):BaseViewState
