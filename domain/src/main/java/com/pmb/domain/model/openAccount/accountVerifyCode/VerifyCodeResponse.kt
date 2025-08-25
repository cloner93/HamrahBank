package com.pmb.domain.model.openAccount.accountVerifyCode

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class VerifyCodeResponse(
    val actionType: Int,
    val annualIncomeTypes: List<AnnualIncomeType>?=null,
    val annualTransactionTypes: List<AnnualTransactionType>?=null,
    val birthCityCode: Int,
    val birthCityName: String?=null,
    val birthDate: Int,
    val cityOfBirthInfoDTOList: List<CityOfBirthInfoDTO>?=null,
    val education: Int,
    val errorCode: Int,
    val issueCityCode: Int,
    val issueCityName: String?=null,
    val issueDate: Int,
    val issueReginCode: Int,
    val job: String?=null,
    val jobCode: Int,
    val metaData: String?=null,
    val tel: String?=null
)