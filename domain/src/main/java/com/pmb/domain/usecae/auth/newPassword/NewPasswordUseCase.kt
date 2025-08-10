package com.pmb.domain.usecae.auth.newPassword

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.changePassword.NewPasswordRequest
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<NewPasswordParams,Boolean>(){
    override suspend fun execute(params: NewPasswordParams): Flow<Result<Boolean>> {
        return authRepository.newPassword(params.toRequest())
    }
}

data class NewPasswordParams(
    val mobileNo: String,
    val nationalCode: String,
    val newPassword: String,
    val reNewPassword: String
) {
    fun toRequest() : NewPasswordRequest {
        return NewPasswordRequest(
            mobileNo = this.mobileNo,
            nationalCode = this.nationalCode,
            newPassword = this.newPassword,
            reNewPassword = this.reNewPassword
        )
    }
}