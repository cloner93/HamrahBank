package com.pmb.ballon.models

data class DepositBottomSheetModel(
    val title: String,
    val desc: String?,
    val amount: Double,
    var selected: Boolean = false,
    val state: Int,
    val depositNumber: String,
    val categoryCode: Long,
    val currency: String,
    val ibanNumber: String,
    val cardNumber: String
)