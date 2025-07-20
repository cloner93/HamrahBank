package com.pmb.data.repository.transaction

import androidx.paging.PagingSource
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.data.serviceProvider.remote.transaction.TransactionByCountPagingSource
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.repository.transactions.TransactionRepository
import jakarta.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider,

    ) : TransactionRepository {
    override fun getTransactionByCountPagingSource(request: TransactionRequest): PagingSource<String, TransactionModel> {
        return TransactionByCountPagingSource(remoteServiceProvider, request)
    }

    override fun getTransactionByDatePagingSource(request: TransactionRequest): PagingSource<String, TransactionModel> {
        return TransactionByCountPagingSource(remoteServiceProvider, request)
    }
}