package com.pmb.auth.presentaion.new_password.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface NewPassWordViewEvents : BaseViewEvent {
    data object ChangePassWordSucceed : NewPassWordViewEvents
}