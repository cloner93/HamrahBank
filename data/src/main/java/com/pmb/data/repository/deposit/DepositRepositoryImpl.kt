package com.pmb.data.repository.deposit

import com.pmb.core.platform.Result
import com.pmb.data.appManager.AppManager
import com.pmb.data.mapper.depositService.toDomain
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.DepositModel
import com.pmb.domain.repository.DepositRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DepositRepositoryImpl @Inject constructor(
    private val appManager: AppManager
) : DepositRepository {
    override fun getDepositList(): Flow<Result<List<DepositModel>>> {
        return appManager.getDepositService().getDepositList().mapApiResult {
                it.second.toDomain()
            }
    }
}
