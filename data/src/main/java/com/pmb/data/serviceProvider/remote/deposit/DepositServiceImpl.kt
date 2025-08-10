package com.pmb.data.serviceProvider.remote.deposit

import com.pmb.core.platform.Result
import com.pmb.domain.model.BalanceModel
import com.pmb.domain.model.BalanceRequest
import com.pmb.domain.model.Deposit
import com.pmb.domain.model.LoginRequest
import com.pmb.model.SuccessData
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DepositServiceImpl @Inject constructor(
    private val client: NetworkManger
) : DepositService {
    override fun getDepositList(): Flow<Result<SuccessData<List<Deposit>>>> {
        return client.request<LoginRequest, List<Deposit>>(endpoint = "account/getUserAccounts")

    }

    override fun getBalanceOfDeposit(balance: BalanceRequest): Flow<Result<SuccessData<BalanceModel>>> {
        return client.request<BalanceRequest, BalanceModel>(
            endpoint = "account/balance",
            data = balance
        )
    }
}