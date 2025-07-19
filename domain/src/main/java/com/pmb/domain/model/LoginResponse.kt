package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class LoginResponse(
    val birthDate: Int,
    val currentGrade: Int,
    val customerId: Long,
    val customerNumber: Long,
    val customerType: Int,
    val desKey: String?,
    val email: String?,
    val family: String?,
    val faragirNumber: String?,
    val fatherName: String?,
    val gender: String?,
    val imeiNO: String?,
    val immigrationNumber: Int,
    val lastChangePassDate: Int,
    val lastChangePassTime: Int,
    val lastLoginDate: Int,
    val lastLoginTime: Int,
    val lockDate: Int,
    val lockTime: Int,
    val name: String?,
    val nationalCode: Int,
    val passportNumber: String?,
    val passwordX: String?,
    val postalCode: String?,
    val sabtQueryStatus: Int,
    val securePassword: String?,
    val userName: String?,
    val vcode: Int
)
