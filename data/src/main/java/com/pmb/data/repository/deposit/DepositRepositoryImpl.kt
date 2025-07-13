package com.pmb.data.repository.deposit

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.Deposit
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.LoginRequest
import com.pmb.domain.repository.DepositRepository
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DepositRepositoryImpl @Inject constructor(
    private val client: NetworkManger
) : DepositRepository {
    override fun getDepositList(): Flow<Result<List<DepositModel>>> {
        return client.request<LoginRequest, List<Deposit>>(endpoint = "account/getUserAccounts")
            .mapApiResult {
                it.second.toDomain()
            }
    }
}

private fun List<Deposit>.toDomain(): List<DepositModel> {
    val listOfDeposit = mutableListOf<DepositModel>()
    this.forEach {
        listOfDeposit.add(
            DepositModel(
                title = it.accountTypeDescription ?: "N/A",
                desc = it.organizationName,
                categoryCode = it.categoryCode,
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