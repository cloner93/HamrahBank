package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable


@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class CardCustomerAddressResponse(
    val output: Output,
    val result: List<Result>
)

@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class Output(
    val address: String,
    val birthDate: Int,
    val expireDate: Int,
    val family: String,
    val fatherName: String,
    val gender: String,
    val idNumber: String,
    val issueRegionCode: Int,
    val name: String,
    val nationalCode: Int,
    val postalCode: String,
    val postalCost: Int,
    val relatedCustomerId: Int,
    val requestNumber: Int,
    val requestType: Int
)

@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class Result(
    val pan: Int
)


