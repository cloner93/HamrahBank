package com.pmb.domain.model

data class DepositModel(
    val title: String,
    val desc: String?,
    val depositNumber: String,
    val amount: Double,
    val currency: String,
    val ibanNumber: String,
    val cardNumber: String,
    var isSelected: Boolean = false
)