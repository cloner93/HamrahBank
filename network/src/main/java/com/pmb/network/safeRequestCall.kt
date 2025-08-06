package com.pmb.network

import MobileApiResponse
import com.pmb.core.platform.Result
import com.pmb.model.SuccessData
import com.pmb.network.plugin.ApiException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

inline fun <reified Response> safeRequestCall(
    crossinline request: suspend () -> MobileApiResponse<Response>
): Flow<Result<SuccessData<Response>>> = flow {
    emit(Result.Loading)

    val response = request()

    if (response.status != 0) {
        val message = response.metaData?.statusMessage ?: "خطای ناشناخته"
        throw ApiException(message)
    }
//    delay(4000)

    emit(
        Result.Success(
            SuccessData(response.data, response.metaData)
        )
    )

}.catch { e ->
    emit(
        Result.Error(
            message = e.message ?: "خطای ناشناخته",
            exception = e
        )
    )
}