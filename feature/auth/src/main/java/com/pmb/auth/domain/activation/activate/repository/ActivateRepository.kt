package com.pmb.auth.domain.activation.activate.repository

import com.pmb.auth.domain.activation.activate.entity.ActivateEntity
import com.pmb.auth.domain.activation.activate.entity.ActivateParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface ActivateRepository {
    suspend fun activeUser(activateParams: ActivateParams): Flow<Result<ActivateEntity>>
}