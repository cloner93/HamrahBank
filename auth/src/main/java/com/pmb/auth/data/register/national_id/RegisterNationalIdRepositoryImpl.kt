package com.pmb.auth.data.register.national_id

import com.pmb.auth.domain.register.national_id.entity.RegisterNationalIdEntity
import com.pmb.auth.domain.register.national_id.entity.RegisterNationalIdRequest
import com.pmb.auth.domain.register.national_id.repository.RegisterNationalIdRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterNationalIdRepositoryImpl @Inject constructor() : RegisterNationalIdRepository {
    override suspend fun registerNationalId(params: RegisterNationalIdRequest): Flow<Result<RegisterNationalIdEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            params.nationalSerialId.takeIf { it.isNotEmpty() }?.let {
                emit(Result.Success(RegisterNationalIdEntity(isSuccess = true)))
            } ?: run {
                emit(Result.Error(message = "Your photo's address is not valid"))
            }
        }
}