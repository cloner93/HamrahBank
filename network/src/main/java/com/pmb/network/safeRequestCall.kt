package com.pmb.network

import MobileApiResponse
import com.pmb.core.platform.Result
import com.pmb.network.plugin.ApiException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

inline fun <reified T> safeRequestCall(
    crossinline request: suspend () -> MobileApiResponse<T>
): Flow<Result<T>> = flow {
    emit(Result.Loading)

    val response = request()

    if (response.status != 0 || response.data == null) {
        val message = response.metaData?.statusMessage ?: "خطای ناشناخته"
        throw ApiException(message)
    }

    emit(Result.Success(response.data!!))

}.catch { e ->
    emit(
        Result.Error(
            message = e.message ?: "خطای ناشناخته",
            exception = e
        )
    )
}
