package com.pmb.auth.presentation.register

import com.pmb.core.platform.BaseSharedState
import com.pmb.domain.model.openAccount.FetchAdmittanceTextResponse
import com.pmb.domain.model.openAccount.accountVerifyCode.CityOfBirthInfoDTO
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse

data class RegisterSharedViewState(
    val verifyCodeResponse: VerifyCodeResponse? = null,
    val admittanceTextResponse: FetchAdmittanceTextResponse? = null,
    val accType: Int? = null,//
    val birthDatePlace: CityOfBirthInfoDTO? = null,
    val issuePlace: CityOfBirthInfoDTO? = null,
    val address: String? = null,//
    val birthCityCode: Int? = null,//
    val birthDate: String? = null,//
    val branch: Int? = null,//
    val cardFormatId: Int? = null,//
    val cardReq: Int? = null,//
    val cinCpId: Int? = null,//
    val ctrApId: Int? = null,//
    val education: Int? = null,//
    val intBankReq: Int? = null,//
    val issueCityCode: Int? = null,//
    val issueDate: String? = null,//
    val issueRgnCode: Int? = null,//
    val jobCode: Int? = null,//
    val mobileNo: String? = null,//
    val nationalCode: String? = null,//
    val postcode: Long? = null,//
    val seriMeli: String? = null,//
    val serialMeli: String? = null,//
    val signData: String? = null,
    val tel: String? = null,//
    val authImage: String? = null,
    val authVideo: String? = null,
    val refId :String?=null
) : BaseSharedState
