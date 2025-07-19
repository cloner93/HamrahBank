package com.pmb.domain.model.openAccount.accountVerifyCode

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class VerifyCodeResponse(
    val actionType: Int,
    val annualIncomeTypes: List<AnnualIncomeType>,
    val annualTransactionTypes: List<AnnualTransactionType>,
    val birthCityCode: Int,
    val birthCityName: String,
    val birthDate: Int,
    val cityOfBirthInfoDTOList: List<CityOfBirthInfoDTO>,
    val education: Int,
    val errorCode: Int,
    val issueCityCode: Int,
    val issueCityName: String,
    val issueDate: Int,
    val issueReginCode: Int,
    val job: String,
    val jobCode: Int,
    val metaData: String,
    val tel: String
)