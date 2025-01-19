package com.pmb.auth.domain.new_password.useCase

import com.pmb.auth.domain.new_password.entity.NewPasswordEntity
import com.pmb.auth.domain.new_password.entity.NewPasswordParams
import com.pmb.auth.domain.new_password.repository.NewPasswordRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewPasswordUseCase @Inject constructor(
    private val newPasswordRepository: NewPasswordRepository
) : BaseUseCase<NewPasswordParams, NewPasswordEntity>() {
    override suspend fun execute(params: NewPasswordParams): Flow<Result<NewPasswordEntity>> {
        return newPasswordRepository.changePassWord(newPasswordParams = params)
    }
}