package com.pmb.data.repository.transaction.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.mapper.transactionService.mapToDomain
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.first

class TransactionByCountPagingSource(
    private val remote: RemoteServiceProvider,
    private val baseRequest: TransactionRequest
) : PagingSource<String, TransactionModel>() {
    private var lastReturnedKey: String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, TransactionModel> {
        val offset = params.key ?: ""
        val request = baseRequest.copy(offset = offset)

        return try {
            val responseFlow =
                remote.getTransactionService()
                    .getTransactionByCountPaging(request)
                    .filterNot { it is Result.Loading }
                    .mapApiResult { it.second.mapToDomain() }

            val result = responseFlow.first()

            return when (result) {
                is Result.Success -> {
                    val data = result.data

                    val cleanedItems = if (offset.isNotBlank()) {
                        data.filter { it.incCode.toString() != offset }
                    } else {
                        data
                    }

                    val newKey = cleanedItems.lastOrNull()?.incCode?.also {
                        println("nextKey $it")
                    }
                    val shouldStop = cleanedItems.isEmpty() ||
                            (cleanedItems.size == 1 && newKey != null && newKey == lastReturnedKey) ||
                            (cleanedItems.size == 1 && "0" == lastReturnedKey)

                    LoadResult.Page(
                        data = cleanedItems,
                        prevKey = null,
                        nextKey = if (shouldStop) null else newKey
                    )

                }

                is Result.Error -> {
                    LoadResult.Error(Throwable(result.message))
                }

                is Result.Loading -> {
                    LoadResult.Error(Throwable("Loading not supported"))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, TransactionModel>): String? {
        return null
    }
}