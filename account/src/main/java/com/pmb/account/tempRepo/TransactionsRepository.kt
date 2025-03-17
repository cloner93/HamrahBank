package com.pmb.account.tempRepo

import com.pmb.account.presentation.component.TransactionModel
import com.pmb.account.presentation.component.TransactionType
import javax.inject.Inject

class TransactionsRepository @Inject constructor() {
    suspend fun getTransactionsForDeposit(depositId: String): List<TransactionModel> {
        return when (depositId) {
            "123456" -> listOf(
                TransactionModel(
                    TransactionType.RECEIVE,
                    "واریز حقوق",
                    15_000_000.0,
                    "ریال",
                    "امروز ساعت ۱۰:۳۰"
                ),
                TransactionModel(
                    TransactionType.WITHDRAWAL,
                    "برداشت از خودپرداز",
                    2_000_000.0,
                    "ریال",
                    "دیروز ساعت ۱۸:۲۰"
                ),
                TransactionModel(
                    TransactionType.TRANSFER,
                    "انتقال به حساب دیگر",
                    3_500_000.0,
                    "ریال",
                    "۳ روز پیش ساعت ۱۴:۱۵"
                )
            )

            "97974632" -> listOf(
                TransactionModel(
                    TransactionType.RECEIVE,
                    "سود سپرده بلندمدت",
                    12_500_000.0,
                    "ریال",
                    "هفته گذشته"
                ),
                TransactionModel(
                    TransactionType.WITHDRAWAL,
                    "برداشت برای سرمایه‌گذاری",
                    50_000_000.0,
                    "ریال",
                    "۲ هفته پیش"
                ),
                TransactionModel(
                    TransactionType.TRANSFER,
                    "انتقال از حساب دیگر",
                    30_000_000.0,
                    "ریال",
                    "۳ هفته پیش"
                )
            )

            "82768947" -> listOf(
                TransactionModel(
                    TransactionType.RECEIVE,
                    "واریز شریک",
                    5_000_000.0,
                    "ریال",
                    "امروز ساعت ۰۹:۰۰"
                ),
                TransactionModel(
                    TransactionType.WITHDRAWAL,
                    "برداشت نقدی",
                    1_500_000.0,
                    "ریال",
                    "دیروز ساعت ۱۷:۳۰"
                ),
                TransactionModel(
                    TransactionType.TRANSFER,
                    "پرداخت اجاره",
                    8_000_000.0,
                    "ریال",
                    "۵ روز پیش"
                )
            )

            "23879" -> listOf(
                TransactionModel(
                    TransactionType.RECEIVE,
                    "دریافت حواله ارزی",
                    500.0,
                    "$",
                    "دیروز ساعت ۱۶:۴۵"
                ),
                TransactionModel(
                    TransactionType.WITHDRAWAL,
                    "پرداخت اینترنتی",
                    120.0,
                    "$",
                    "امروز ساعت ۲۰:۱۰"
                ),
                TransactionModel(
                    TransactionType.TRANSFER,
                    "انتقال به حساب داخلی",
                    200.0,
                    "$",
                    "هفته گذشته"
                )
            )

            "746384" -> listOf(
                TransactionModel(
                    TransactionType.RECEIVE,
                    "واریز نقدی",
                    4_000_000.0,
                    "ریال",
                    "دیروز"
                ),
                TransactionModel(
                    TransactionType.WITHDRAWAL,
                    "برداشت برای خرید",
                    2_500_000.0,
                    "ریال",
                    "۲ روز پیش"
                ),
                TransactionModel(
                    TransactionType.TRANSFER,
                    "انتقال به کارت",
                    1_000_000.0,
                    "ریال",
                    "۳ روز پیش"
                )
            )

            "920384" -> listOf(
                TransactionModel(
                    TransactionType.RECEIVE,
                    "هدیه خانواده",
                    3_000_000.0,
                    "ریال",
                    "هفته پیش"
                ),
                TransactionModel(
                    TransactionType.WITHDRAWAL,
                    "پرداخت شهریه",
                    1_800_000.0,
                    "ریال",
                    "۱۰ روز پیش"
                ),
                TransactionModel(
                    TransactionType.TRANSFER,
                    "انتقال به حساب والدین",
                    500_000.0,
                    "ریال",
                    "۱۲ روز پیش"
                )
            )

            "348291" -> listOf(
                TransactionModel(
                    TransactionType.RECEIVE,
                    "درآمد کسب‌وکار",
                    120_000_000.0,
                    "ریال",
                    "امروز ساعت ۱۲:۴۵"
                ),
                TransactionModel(
                    TransactionType.WITHDRAWAL,
                    "پرداخت مالیات",
                    40_000_000.0,
                    "ریال",
                    "۳ روز پیش"
                ),
                TransactionModel(
                    TransactionType.TRANSFER,
                    "انتقال به حساب شرکت",
                    70_000_000.0,
                    "ریال",
                    "هفته گذشته"
                )
            )

            "736452" -> listOf(
                TransactionModel(
                    TransactionType.RECEIVE,
                    "دریافت ارز از خارج",
                    2_000.0,
                    "$",
                    "امروز ساعت ۱۵:۲۰"
                ),
                TransactionModel(TransactionType.WITHDRAWAL, "پرداخت اقساط", 1_200.0, "$", "دیروز"),
                TransactionModel(
                    TransactionType.TRANSFER,
                    "انتقال به حساب داخلی",
                    500.0,
                    "$",
                    "۴ روز پیش"
                )
            )

            "572839" -> listOf(
                TransactionModel(
                    TransactionType.RECEIVE,
                    "واریز خانوادگی",
                    6_000_000.0,
                    "ریال",
                    "هفته گذشته"
                ),
                TransactionModel(
                    TransactionType.WITHDRAWAL,
                    "برداشت ماهانه",
                    2_000_000.0,
                    "ریال",
                    "۱۰ روز پیش"
                ),
                TransactionModel(
                    TransactionType.TRANSFER,
                    "انتقال به حساب فرزند",
                    1_500_000.0,
                    "ریال",
                    "دیروز"
                )
            )

            "827364" -> listOf(
                TransactionModel(TransactionType.RECEIVE, "درآمد ارزی", 1_500.0, "€", "دیروز"),
                TransactionModel(
                    TransactionType.WITHDRAWAL,
                    "خرید اینترنتی",
                    250.0,
                    "€",
                    "۵ روز پیش"
                ),
                TransactionModel(
                    TransactionType.TRANSFER,
                    "انتقال به حساب ارزی دیگر",
                    750.0,
                    "€",
                    "۲ هفته پیش"
                )
            )

            else -> emptyList()
        }
    }
}
