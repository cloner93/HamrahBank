package com.pmb.core.network

import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

inline fun <T> safeRequestCall(
    crossinline request: suspend () -> T
): Flow<Result<T>> = flow {
    emit(Result.Loading)
    emit(Result.Success(request()))
}.catch { e ->
    emit(
        Result.Error(
            message = e.message ?: "Unknown error",
            exception = e
        )
    )
}
