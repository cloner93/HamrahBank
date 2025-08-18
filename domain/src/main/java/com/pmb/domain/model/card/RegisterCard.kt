package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable


@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RegisterCardRequest(
    val cardGroup: Long,
    val accountType: Long,
    val issueRegionCode: Long,
    val requestType: Long,
    val incomeType: Long,
    val birthDate: Long,
    val lastPanExpireDate: Long,
    val cityCode: Long,
    val formatId: Long,
    val accountNumber: Long,
    val accountId: Long,
    val commissionAccountNumber: Long,
    val nationalCode: Long,
    val pan: Long,
    val gender: String?,
    val name: String?,
    val fatherName: String?,
    val postalCode: String?,
    val idNumber: String?,
    val family: String?,
    val address: String?
)


@SuppressLint("UnsafeOptInUsageError")
@Serializable

data class RegisterCardResponse(
    val requestNumber: Int,
    val transactionNumber: Int,
    val followId: Int,
    val totalAmount: Int,
    val formatCardAmount: Int

)
