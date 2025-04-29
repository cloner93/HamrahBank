package com.pmb.profile.presentaion.profile.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface ProfileViewActions : BaseViewAction {
    data object ClearAlert : ProfileViewActions
    data object LogoutAccount : ProfileViewActions
    object NavigateToThemeScreen : ProfileViewActions
}