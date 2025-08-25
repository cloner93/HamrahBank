package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RegisterVerifyResponse(
    val customerId: Long,
    val desKey: String,
    val passwordX: String,
    val securePassword: String,
    val userName: String,
    val email: String?,
    val imeiNO: String,
    val lastLoginDate: Long,
    val lastLoginTime: Long,
    val lastChangePassDate: Long,
    val lastChangePassTime: Long,
    val lockDate: Long,
    val lockTime: Long,
    val currentGrade: Long,
    val immigrationNumber: Long,
    val birthDate: Long,
    val customerType: Long,
    val nationalCode: Long,
    val customerNumber: Long,
    val gender: String,
    val postalCode: String,
    val faragirNumber: String,
    val name: String,
    val family: String,
    val fatherName: String,
    val sabtQueryStatus: Long,
    val passportNumber: String,
    val vcode: Long,
)