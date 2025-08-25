package com.pmb.auth.domain.new_password.repository

import com.pmb.auth.domain.new_password.entity.NewPasswordEntity
import com.pmb.auth.domain.new_password.entity.NewPasswordParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface NewPasswordRepository {
    fun changePassWord(newPasswordParams: NewPasswordParams): Flow<Result<NewPasswordEntity>>
}