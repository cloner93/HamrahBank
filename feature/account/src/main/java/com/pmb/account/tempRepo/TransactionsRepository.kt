package com.pmb.account.tempRepo

import com.pmb.account.presentation.component.TransactionModel
import com.pmb.account.presentation.component.TransactionType
import javax.inject.Inject

class TransactionsRepository @Inject constructor() {
    suspend fun getTransactionsForDeposit(depositId: String): List<TransactionModel> {
        return when (depositId) {
            "123456" -> listOf(
                TransactionModel(
                    "0",
                    TransactionType.RECEIVE,
                    "واریز حقوق",
                    1_000_000.0,
                    "ریال",
                    "امروز ساعت ۱۰:۳۰"
                ),
                TransactionModel(
                    "1",
                    TransactionType.RECEIVE,
                    "واریز حقوق",
                    5_000_000.0,
                    "ریال",
                    "امروز ساعت ۱۰:۳۰"
                ),
                TransactionModel(
                    "2",
                    TransactionType.RECEIVE,
                    "واریز حقوق",
                    9_000_000.0,
                    "ریال",
                    "امروز ساعت ۱۰:۳۰"
                ),
                TransactionModel(
                    "3",
                    TransactionType.TRANSFER,
                    "انتقال به حساب دیگر",
                    3_500_000.0,
                    "ریال",
                    "۳ روز پیش ساعت ۱۴:۱۵"
                ),
                TransactionModel(
                    "4",
                    TransactionType.TRANSFER,
                    "انتقال به حساب دیگر",
                    500_000.0,
                    "ریال",
                    "۳ روز پیش ساعت ۱۴:۱۵"
                ),
                TransactionModel(
                    "5",
                    TransactionType.TRANSFER,
                    "انتقال به حساب دیگر",
                    900_000.0,
                    "ریال",
                    "۳ روز پیش ساعت ۱۴:۱۵"
                )
            )

            "97974632" -> listOf(
                TransactionModel(
                    "0",
                    TransactionType.RECEIVE,
                    "سود سپرده بلندمدت",
                    12_500_000.0,
                    "ریال",
                    "هفته گذشته"
                ),
                TransactionModel(
                    "1",
                    TransactionType.WITHDRAWAL,
                    "برداشت برای سرمایه‌گذاری",
                    50_000_000.0,
                    "ریال",
                    "۲ هفته پیش"
                ),
                TransactionModel(
                    "2",
                    TransactionType.TRANSFER,
                    "انتقال از حساب دیگر",
                    30_000_000.0,
                    "ریال",
                    "۳ هفته پیش"
                )
            )

            "82768947" -> listOf(
                TransactionModel(
                    "0",
                    TransactionType.RECEIVE,
                    "واریز شریک",
                    5_000_000.0,
                    "ریال",
                    "امروز ساعت ۰۹:۰۰"
                ),
                TransactionModel(
                    "1",
                    TransactionType.WITHDRAWAL,
                    "برداشت نقدی",
                    1_500_000.0,
                    "ریال",
                    "دیروز ساعت ۱۷:۳۰"
                ),
                TransactionModel(
                    "2",
                    TransactionType.TRANSFER,
                    "پرداخت اجاره",
                    8_000_000.0,
                    "ریال",
                    "۵ روز پیش"
                )
            )

            else -> emptyList()
        }
    }

    suspend fun searchTransactionsInDeposit(
        depositId: String,
        query: String
    ): List<TransactionModel> {
        // FIXME: edit it
        return getTransactionsForDeposit(depositId).filter { transaction ->
            transaction.title.contains(query, ignoreCase = true) ||
                    transaction.amount.toString().contains(query, ignoreCase = true) ||
                    transaction.date.contains(query, ignoreCase = true)
        }
    }

    suspend fun getTransactionById(depositId: String?, transactionId: String?): TransactionModel? {
        if (depositId == null) return null
        if (transactionId == null) return null
        return getTransactionsForDeposit(depositId)
            .find { it.transactionId == transactionId }
    }
}
