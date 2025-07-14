package com.pmb.data.mapper.depositService

import com.pmb.domain.model.Deposit
import com.pmb.domain.model.DepositModel


fun List<Deposit>.toDomain(): List<DepositModel> {
    val listOfDeposit = mutableListOf<DepositModel>()
    this.forEach {
        listOfDeposit.add(
            DepositModel(
                title = it.accountTypeDescription ?: "N/A",
                desc = it.organizationName,
                depositNumber = it.accountNumber.toString(),
                amount = it.balance.toDouble(),
                currency = "ریال",
                ibanNumber = it.shebaNo.toString(),
                cardNumber = ""
            )
        )
    }

    return listOfDeposit
}