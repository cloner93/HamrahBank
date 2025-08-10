package com.pmb.domain.usecae.auth.newPassword

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.FetchAdmittanceTextResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewPasswordFetchAdmittanceTextUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<Unit,FetchAdmittanceTextResponse>(){
    override suspend fun execute(params: Unit): Flow<Result<FetchAdmittanceTextResponse>> {
        return authRepository.newPasswordFetchAdmittanceText()
    }
}