package com.pmb.auth.domain.ekyc.open_account.useCase

import com.pmb.auth.domain.ekyc.open_account.entity.OpenAccountEntity
import com.pmb.auth.domain.ekyc.open_account.repository.OpenAccountRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpenAccountUseCase @Inject constructor(
    private val openAccountRepository: OpenAccountRepository
) :BaseUseCase<Unit,OpenAccountEntity>(){
    override suspend fun execute(params: Unit): Flow<Result<OpenAccountEntity>> {
        return openAccountRepository.openAccount()
    }
}