package com.pmb.domain.repository.transactions

import androidx.paging.PagingSource
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest

interface TransactionRepository {
    fun getPagingSource(request: TransactionRequest): PagingSource<String, TransactionModel>
}