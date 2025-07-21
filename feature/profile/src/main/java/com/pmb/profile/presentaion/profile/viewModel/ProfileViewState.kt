package com.pmb.profile.presentaion.profile.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.UserData

data class ProfileViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val userData: UserData? = null,
    val isUserSignedIn: Boolean = false
) : BaseViewState
