package com.pmb.account.utils

import com.pmb.account.presentation.component.DepositModel
import com.pmb.ballon.models.DepositBottomSheetModel


internal fun DepositBottomSheetModel.mapToDepositModel(): DepositModel {
    return DepositModel(
        title = this.title,
        depositNumber = this.depositNumber,
        amount = this.amount,
        currency = this.currency,
        ibanNumber = this.ibanNumber,
        cardNumber = this.cardNumber
    )
}

internal fun List<DepositModel>.mapToDepositMenu(): List<DepositBottomSheetModel> {
    val list = mutableListOf<DepositBottomSheetModel>()
    for (item in this) {
        val deposit = DepositBottomSheetModel(
            title = item.title,
            desc = "اقزودن نام",
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