package com.pmb.auth.presentation.foget_password.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState


data class ForgetPasswordViewState(
    val loading: Boolean = false,
    val alert: AlertModelState? = null,
    var showBottomSheet: Boolean = false,
    var mobile: String? = null,
    var nationalId: String? = null,
    var password: String? = null,
    var rePassword: String? = null,
) : BaseViewState
