package com.pmb.auth.presentation.reentry.reentry_password.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface ReentryPasswordViewActions : BaseViewAction {
    data object ClearAlert : ReentryPasswordViewActions
    data class ReentryPassword(val password: String) : ReentryPasswordViewActions
}