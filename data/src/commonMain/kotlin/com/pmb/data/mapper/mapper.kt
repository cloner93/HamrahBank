package com.pmb.data.mapper

import com.pmb.core.platform.Result
import com.pmb.model.ResponseMetaData
import com.pmb.model.SuccessData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <Dto, Domain> Flow<Result<SuccessData<Dto>>>.mapApiResult(
    mapper: suspend (Pair<ResponseMetaData?, Dto>) -> Domain
): Flow<Result<Domain>> {
    return map { result ->
        when (result) {
            is Result.Success -> {
                Result.Success(mapper(Pair(result.data.metaData, result.data.data as Dto)))
            }

            is Result.Error -> result
            is Result.Loading -> result
        }
    }
}