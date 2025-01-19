package com.pmb.auth.presentaion.first_login_confirm.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface FirstLoginConfirmViewEvents : BaseViewEvent {
    data object FirstLoginConfirmSucceed : FirstLoginConfirmViewEvents
}