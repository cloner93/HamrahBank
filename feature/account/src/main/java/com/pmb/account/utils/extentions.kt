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
        cardNumber = this.cardNumber,
        accountId = 1,
        accountType = 2
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
            cardNumber = item.cardNumber,
            categoryCode = item.categoryCode
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