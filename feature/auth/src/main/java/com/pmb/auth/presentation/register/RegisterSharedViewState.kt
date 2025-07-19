package com.pmb.auth.presentation.register

import com.pmb.core.platform.BaseSharedState
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse

data class RegisterSharedViewState(
    val verifyCodeResponse: VerifyCodeResponse?=null,
    val phoneNumber:String,
    val serialId :String,
    val nationalId : String,
): BaseSharedState
