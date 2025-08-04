package com.pmb.profile.presentaion.profile.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface ProfileViewActions : BaseViewAction {
    data object ClearAlert : ProfileViewActions
    data object LogoutAccount : ProfileViewActions
    data object NavigateToThemeScreen : ProfileViewActions
    data object NavigateToUpdate : ProfileViewActions
    data object ShowGuideBottomSheet : ProfileViewActions
    data object CloseGuideBottomSheet : ProfileViewActions

    data class ShowSupportBottomSheet(val show: Boolean) : ProfileViewActions
    data class ShowInviteFriendBottomSheet(val show: Boolean) : ProfileViewActions
}