package com.pmb.auth.presentation.ekyc

import com.pmb.core.platform.BaseSharedState

data class EKYCSharedViewState(
    val changePasswordPhoneNumber: String ="",
    val changePasswordPassword: String ="",
    val changePasswordNationalId: String ="",
    val authImage: String? = null,
    val authVideo: String? = null,
    val cardSerialNo :String?=null
) : BaseSharedState