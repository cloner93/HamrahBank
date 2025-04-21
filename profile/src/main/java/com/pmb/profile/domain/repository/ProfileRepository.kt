package com.pmb.profile.domain.profile.repository

import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.PersonalInfoEntity
import com.pmb.profile.domain.use_case.PersonalInfoUseCase
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun logOut(): Flow<Result<String>>
    suspend fun fetchPersonalInfo(params: PersonalInfoUseCase.Param): Flow<Result<PersonalInfoEntity>>
}