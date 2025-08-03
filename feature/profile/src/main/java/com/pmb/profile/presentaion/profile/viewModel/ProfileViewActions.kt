package com.pmb.profile.presentaion.profile.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface ProfileViewActions : BaseViewAction {
    data object ClearAlert : ProfileViewActions
    data object LogoutAccount : ProfileViewActions
    object NavigateToThemeScreen : ProfileViewActions
    object NavigateToUpdate : ProfileViewActions
    data class ShowSupportBottomSheet(val show: Boolean) : ProfileViewActions
    data class ShowInviteFriendBottomSheet(val show: Boolean) : ProfileViewActions
    object AboutAppClicked : ProfileViewActions
    object CommentsSuggestionsClicked : ProfileViewActions
}