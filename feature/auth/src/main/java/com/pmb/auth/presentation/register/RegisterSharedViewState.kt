package com.pmb.auth.presentation.register

import com.pmb.auth.domain.Education
import com.pmb.core.platform.BaseSharedState
import com.pmb.domain.model.openAccount.accountVerifyCode.AnnualIncomeType
import com.pmb.domain.model.openAccount.accountVerifyCode.CityOfBirthInfoDTO
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse

data class RegisterSharedViewState(
    val verifyCodeResponse: VerifyCodeResponse?=null,
    val phoneNumber:String?=null,
    val serialId :String?=null,
    val nationalId : String?=null,
    val birthDatePlace: CityOfBirthInfoDTO? = null,
    val issuePlace: CityOfBirthInfoDTO? = null,
    val issueDate: Int? = null,
    val issueCode: Int? = null,
    val tel: String? = null,
    val education: Education? = null,
    val annualIncomeType: AnnualIncomeType ?=null
): BaseSharedState
