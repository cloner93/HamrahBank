package com.pmb.data.repository.transaction

import androidx.paging.PagingSource
import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.mapper.transactionService.toDomain
import com.pmb.data.repository.transaction.paging.TransactionByCountPagingSource
import com.pmb.data.repository.transaction.paging.TransactionByDatePagingSource
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.transaztion.Summarize
import com.pmb.domain.repository.transactions.TransactionRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider,

    ) : TransactionRepository {
    override fun getTransactionByCountPagingSource(request: TransactionRequest): PagingSource<String, TransactionModel> {
        return TransactionByCountPagingSource(remoteServiceProvider, request)
    }

    override fun getTransactionByDatePagingSource(request: TransactionRequest): PagingSource<String, TransactionModel> {
        return TransactionByDatePagingSource(remoteServiceProvider, request)
    }

    override fun getSummarize(transactionRequest: TransactionRequest): Flow<Result<List<Summarize>>> {
        return remoteServiceProvider.getTransactionService().getSummarize(transactionRequest)
            .mapApiResult {
                it.second.toDomain()
            }
    }
}