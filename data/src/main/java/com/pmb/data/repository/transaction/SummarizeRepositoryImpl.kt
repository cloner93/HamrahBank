package com.pmb.data.repository.transaction

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.mapper.transactionService.toDomain
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.transaztion.Summarize
import com.pmb.domain.repository.transactions.SummarizeRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class SummarizeRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider
) : SummarizeRepository {
    override fun getSummarize(transactionRequest: TransactionRequest): Flow<Result<List<Summarize>>> {
        return remoteServiceProvider.getTransactionService().getSummarize(transactionRequest)
            .mapApiResult {
                it.second.toDomain()
            }
    }
}

