package com.pmb.data.repository.deposit

import com.pmb.core.platform.Result
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.data.mapper.depositService.toDomain
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.DepositModel
import com.pmb.domain.repository.DepositsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DepositsRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider
) : DepositsRepository {
    override fun getDepositList(): Flow<Result<List<DepositModel>>> {
        return remoteServiceProvider.getDepositService().getDepositList().mapApiResult {
                it.second.toDomain()
            }
    }
}
