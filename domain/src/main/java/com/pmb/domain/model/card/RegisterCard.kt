package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable


@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RegisterCardRequest(
    val accountId: Int,
    val accountNumber: Int,
    val accountType: Int,
    val address: String,
    val birthDate: Int,
    val branchCode: Int,
    val cardGroup: Int,
    val categoryCode: Int,
    val cityCode: Int,
    val commissionAccountNumber: Int,
    val commissionPostInquiry: Int,
    val customerId: Int,
    val customerNumber: Int,
    val customerType: Int,
    val environmentCode: Int,
    val family: String,
    val fatherName: String,
    val formatId: Int,
    val gender: String,
    val idNumber: String,
    val incomeType: Int,
    val issueRegionCode: Int,
    val lastPanExpireDate: Int,
    val name: String,
    val nationalCode: Int,
    val ownerType: Int,
    val pan: Int,
    val postalCode: String,
    val reduplicate: Int,
    val relateType: Int,
    val requestType: Int,
    val userId: String
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
