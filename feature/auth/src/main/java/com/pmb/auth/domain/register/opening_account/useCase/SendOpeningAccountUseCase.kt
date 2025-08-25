package com.pmb.auth.domain.register.opening_account.useCase

import com.pmb.auth.domain.register.opening_account.entity.OpeningAccountEntity
import com.pmb.auth.domain.register.opening_account.entity.OpeningAccountParams
import com.pmb.auth.domain.register.opening_account.repository.OpeningAccountRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendOpeningAccountUseCase @Inject constructor(
    private val openingAccountRepository: OpeningAccountRepository
) : BaseUseCase<OpeningAccountParams, OpeningAccountEntity>() {
    override suspend fun execute(params: OpeningAccountParams): Flow<Result<OpeningAccountEntity>> {
        return openingAccountRepository.sendOpeningAccountData(params)
    }
}