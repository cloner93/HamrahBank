package com.pmb.profile.data.profile.repository

import com.pmb.core.platform.Result
import com.pmb.profile.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {
    override suspend fun logOut(): Flow<Result<String>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(Result.Success("خروج با موفقیت انجام شد"))
    }
}

