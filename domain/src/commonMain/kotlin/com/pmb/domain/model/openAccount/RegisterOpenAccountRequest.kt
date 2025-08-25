package com.pmb.domain.model.openAccount

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RegisterOpenAccountRequest(
    val accType: Int,
    val address: String,
    val birthCityCode: Int,
    val birthDate: String,
    val branch: Int,
    val cardFormatId: Int,
    val cardReq: Int,
    val cinCpId: Int,
    val ctrApId: Int,
    val education: Int,
    val intBankReq: Int,
    val issueCityCode: Int,
    val issueDate: String,
    val issueRgnCode: Int,
    val jobCode: Int,
    val mobileNo: String,
    val nationalCode: String,
    val postcode: Long,
    val seriMeli: String,
    val serialMeli: String,
    val signData: String,
    val admittanceText :String,
    val tel: String,
    val authImage :String,
    val authVideo :String
)