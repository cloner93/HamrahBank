package com.pmb.ballon.models

data class DepositBottomSheetModel(
    val title: String,
    val desc: String,
    val amount: Double,
    val state: Int,
    val depositNumber: String,
    val currency: String,
    val ibanNumber: String,
    val cardNumber: String
)