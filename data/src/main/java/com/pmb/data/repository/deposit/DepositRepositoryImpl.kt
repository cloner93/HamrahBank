package com.pmb.data.repository.deposit

import com.pmb.core.platform.Result
import com.pmb.data.mapper.depositService.toDomain
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.serviceProvider.local.LocalServiceProvider
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.domain.model.DepositModel
import com.pmb.domain.repository.deposit.DepositsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DepositRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider,
    private val localServiceProvider: LocalServiceProvider
) : DepositsRepository {
    override fun getDepositList(): Flow<Result<List<DepositModel>>> {
        return remoteServiceProvider.getDepositService().getDepositList().mapApiResult {
            it.second.toDomain()
        }
    }

    override fun getDefaultDeposit(): Flow<Result<DepositModel>> = flow {
        emit(Result.Loading)
        val depositModel = localServiceProvider.getUserDataStore().getMainDeposit()
        depositModel?.let {
            emit(Result.Success(it))
        }?:run {
            emit(Result.Error(message = "No Default Deposit Account"))
        }
    }

    override fun setDefaultDeposit(depositModel: DepositModel) = flow {
        emit(Result.Loading)
        val result = localServiceProvider.getUserDataStore().setDepositAsMainDeposit(depositModel)
        if (result){
            emit(Result.Success(true))
        }else{
            emit(Result.Error("couldn't set as main deposit"))
        }
    }
}