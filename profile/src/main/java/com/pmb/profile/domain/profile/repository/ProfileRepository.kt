package com.pmb.profile.domain.profile.repository

import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun logOut(): Flow<Result<String>>
}