package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.AccountArchiveJobDocResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountArchiveJobDocUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<AccountArchiveJobDocParams, AccountArchiveJobDocResponse>() {
    override suspend fun execute(params: AccountArchiveJobDocParams): Flow<Result<AccountArchiveJobDocResponse>> {
        return authRepository.accountArchiveJobDoc(
            file = params.file,
            nationalCode = params.nationalCode
        )
    }

}

data class AccountArchiveJobDocParams(
    val file: String,
    val nationalCode: String
)