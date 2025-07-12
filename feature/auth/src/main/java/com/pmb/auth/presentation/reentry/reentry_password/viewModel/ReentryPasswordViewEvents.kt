package com.pmb.auth.presentation.reentry.reentry_password.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface ReentryPasswordViewEvents : BaseViewEvent {
    data object ReentryPasswordSucceed : ReentryPasswordViewEvents
}