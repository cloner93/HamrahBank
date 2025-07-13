package com.pmb.account.utils

import com.pmb.account.presentation.balance.DepositsChartModel
import com.pmb.account.presentation.balance.viewmodel.generateColorByIndex
import com.pmb.ballon.models.DepositBottomSheetModel
import com.pmb.domain.model.DepositModel


internal fun DepositBottomSheetModel.mapToDepositModel(): DepositModel {
    return DepositModel(
        title = this.title,
        desc = this.desc,
        depositNumber = this.depositNumber,
        amount = this.amount,
        currency = this.currency,
        ibanNumber = this.ibanNumber,
        categoryCode = this.categoryCode,
        cardNumber = this.cardNumber
    )
}

internal fun List<DepositModel>.mapToDepositMenu(): List<DepositBottomSheetModel> {
    val list = mutableListOf<DepositBottomSheetModel>()
    for (item in this) {
        val deposit = DepositBottomSheetModel(
            title = item.title,
            desc = item.desc,
            depositNumber = item.depositNumber,
            amount = item.amount,
            currency = item.currency,
            ibanNumber = item.ibanNumber,
            selected = item.isSelected,
            state = 1,
            cardNumber = item.cardNumber
        )
        list.add(deposit)
    }
    return list
}

fun List<DepositModel>.mapToDepositsChartModel(): MutableList<DepositsChartModel> {
    val totalSum = this.sumOf { it.amount }
    return this.mapIndexed { index, it ->
        DepositsChartModel(
            value = (it.amount * 100 / totalSum).toFloat(),
            color = generateColorByIndex(index),
            title = it.title,
            depositNumber = it.depositNumber,
            amount = it.amount,
            currency = it.currency
        )
    }.toMutableList()
}

fun String?.toPersianDate(): String {
    if (this == null || this.length != 8) return ""

    val year = this.substring(0, 4)
    val month = this.substring(4, 6)
    val day = this.substring(6, 8)

    val persianMonths = listOf(
        "فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور",
        "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"
    )

    val monthIndex = month.toIntOrNull()?.minus(1) ?: return ""
    val monthName = persianMonths.getOrNull(monthIndex) ?: return ""

    return " $day $monthName $year"
}
