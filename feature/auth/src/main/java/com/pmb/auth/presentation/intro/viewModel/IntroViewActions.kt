package com.pmb.auth.presentation.intro.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface IntroViewActions : BaseViewAction {
    data object GetUserData : IntroViewActions
}