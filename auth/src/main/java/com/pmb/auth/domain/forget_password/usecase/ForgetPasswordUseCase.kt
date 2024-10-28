package com.pmb.auth.domain.forget_password.usecase

import com.pmb.auth.domain.forget_password.entity.ForgetPasswordEntity
import com.pmb.auth.domain.forget_password.repository.ForgetPasswordRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForgetPasswordUseCase @Inject constructor(
   private val forgetPasswordRepository: ForgetPasswordRepository
) : BaseUseCase<ForgetPasswordParams,ForgetPasswordEntity>(){
    override suspend fun execute(params: ForgetPasswordParams): Flow<Result<ForgetPasswordEntity>> {
        return forgetPasswordRepository.forgetPassword(params.mobileNumber,params.nationalId,params.password)
    }
}

data class ForgetPasswordParams(
    val mobileNumber:String,
    val nationalId:String,
    val password:String
)