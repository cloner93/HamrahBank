package com.pmb.domain.model.transaztion

import com.pmb.domain.model.TransactionModel

data class Summarize(
    val docDesc: String,
    val totalAmount: String = "0",
    val transactionList: List<TransactionModel>
)