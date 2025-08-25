package com.pmb.profile.presentaion.profile.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface ProfileViewEvents : BaseViewEvent {
    data object LogoutAccountSucceed : ProfileViewEvents
    object NavigateToThemeScreen : ProfileViewEvents
    object NavigateToUpdate : ProfileViewEvents
    object NavigateToAboutApp : ProfileViewEvents
    object NavigateToCommentsSuggestions : ProfileViewEvents
}