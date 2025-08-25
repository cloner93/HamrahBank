package com.pmb.domain.usecae.transactions

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.repository.transactions.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionsByCountPagingUsaCase @Inject constructor(
    private val repository: TransactionRepository
) {
    operator fun invoke(params: TransactionRequest): Flow<PagingData<TransactionModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { repository.getTransactionByCountPagingSource(params) }
        ).flow
    }
}