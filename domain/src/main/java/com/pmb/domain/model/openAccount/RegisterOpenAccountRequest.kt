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
    val cardIssueType: Int,
    val cardReq: Int,
    val cinCpId: Int,
    val ctrApId: Int,
    val education: Int,
    val envCode: Int,
    val intBankReq: Int,
    val issueCityCode: Int,
    val issueDate: String,
    val issueRgnCode: Int,
    val jobCode: Int,
    val mobBankReq: Int,
    val mobileNo: String,
    val nationalCode: String,
    val postcode: Long,
    val refId: Long,
    val seriMeli: String,
    val serialMeli: String,
    val signData: String,
    val tel: String,
    val jobFirstLevelCode: Int,
    val jobSecondLevelCode: Int,
    val jobThirdLevelCode: Int
)