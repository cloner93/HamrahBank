package com.pmb.auth.domain.register.national_id.useCase

import com.pmb.auth.domain.register.national_id.entity.RegisterNationalIdEntity
import com.pmb.auth.domain.register.national_id.entity.RegisterNationalIdRequest
import com.pmb.auth.domain.register.national_id.repository.RegisterNationalIdRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterNationalIdUseCase @Inject constructor(
    private val registerNationalIdRepository: RegisterNationalIdRepository
) : BaseUseCase<RegisterNationalIdRequest, RegisterNationalIdEntity>() {
    override suspend fun execute(params: RegisterNationalIdRequest): Flow<Result<RegisterNationalIdEntity>> =
        registerNationalIdRepository.registerNationalId(params)

}