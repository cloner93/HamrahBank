package com.pmb.domain.repository.transactions

import androidx.paging.PagingSource
import com.pmb.core.platform.Result
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.transaztion.Summarize
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactionByCountPagingSource(request: TransactionRequest): PagingSource<String, TransactionModel>
    fun getTransactionByDatePagingSource(request: TransactionRequest): PagingSource<String, TransactionModel>
    fun getSummarize(transactionRequest: TransactionRequest): Flow<Result<List<Summarize>>>
}