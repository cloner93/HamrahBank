package com.pmb.profile.presentaion.profile.viewModel

import com.pmb.ballon.models.AccountSampleModel
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class ProfileViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val userData :AccountSampleModel?=null,
    val isUserSignedIn: Boolean = false
) : BaseViewState
