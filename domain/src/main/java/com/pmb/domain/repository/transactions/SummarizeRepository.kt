package com.pmb.domain.repository.transactions

import com.pmb.core.platform.Result
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.transaztion.Summarize
import kotlinx.coroutines.flow.Flow

interface SummarizeRepository {

    fun getSummarize(transactionRequest: TransactionRequest): Flow<Result<List<Summarize>>>

}