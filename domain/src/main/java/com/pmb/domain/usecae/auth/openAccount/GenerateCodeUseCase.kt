package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<GenerateCodeParams,Boolean>() {
    override suspend fun execute(params: GenerateCodeParams): Flow<Result<Boolean>> {
       return authRepository.generateCode(
            nationalCode = params.nationalCode,
            mobileNo = params.mobileNo,
            birthDate = params.birthDate
        )
    }
}

data class GenerateCodeParams(
    val nationalCode: String,
    val mobileNo: String,
    val birthDate: String
)