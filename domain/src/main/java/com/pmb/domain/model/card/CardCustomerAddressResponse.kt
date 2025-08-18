package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable


@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class CardCustomerAddressResponse(
    val output: Output,
    val result: List<Result>?
)

@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class Output(
    val address: String?,
    val birthDate: Long,
    val expireDate: Long,
    val family: String?,
    val fatherName: String?,
    val gender: String?,
    val idNumber: String?,
    val issueRegionCode: Long,
    val name: String?,
    val nationalCode: Long,
    val postalCode: String?,
    val postalCost: Long,
    val relatedCustomerId: Long,
    val requestNumber: Long,
    val requestType: Long
)

@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class Result(
    val pan: Long
)


