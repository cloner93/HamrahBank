package com.pmb.domain.usecae.transactions

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.transaztion.Summarize
import com.pmb.domain.repository.transactions.SummarizeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSummarizeUseCase @Inject constructor(
    private val repository: SummarizeRepository
) : BaseUseCase<TransactionRequest, List<Summarize>>() {
    override suspend fun execute(params: TransactionRequest): Flow<Result<List<Summarize>>> {
        return repository.getSummarize(params)
    }
}