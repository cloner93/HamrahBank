package com.pmb.data.mapper

import com.pmb.calender.formatPersianDateForDisplay
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionResponse
import com.pmb.domain.model.TransactionType


fun List<TransactionResponse>.mapToDomain(): List<TransactionModel> {
    val listOfTransaction = mutableListOf<TransactionModel>()
    this.forEach { it ->
        var amount: Long = 0
        var type = TransactionType.UNKNOWN

        if (it.creditAmount != 0.toLong()) {
            amount = it.creditAmount
            type = TransactionType.RECEIVE
        } else if (it.debitAmount != 0.toLong()) {
            amount = it.debitAmount
            type = TransactionType.TRANSFER
        }

        val transactionTime = it.time ?: "000000"

        val date = formatPersianDateForDisplay(it.transactionDate.toString(), transactionTime)

        listOfTransaction.add(
            TransactionModel(
                transactionId = it.transactionNumber.toString(),
                type = type,
                amount = amount.toDouble(),
                currency = "ریال",
                title = it.docDesc ?: "نامشخص",
                incCode = it.incCode,
                date = date
            )
        )

    }
    return listOfTransaction
}