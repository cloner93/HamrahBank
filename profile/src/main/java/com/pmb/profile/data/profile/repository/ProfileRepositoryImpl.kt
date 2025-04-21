package com.pmb.profile.data.profile.repository

import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.PersonalInfoEntity
import com.pmb.profile.domain.profile.repository.ProfileRepository
import com.pmb.profile.domain.use_case.PersonalInfoUseCase
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

    override suspend fun fetchPersonalInfo(params: PersonalInfoUseCase.Param): Flow<Result<PersonalInfoEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(
            Result.Success(
                PersonalInfoEntity(
                    username = "pooriak",
                    phoneNumber = "09123456789",
                    address = "تهران، کوی نصر، خیابانکوی نصر",
                    education = "دانشجو",
                    job = "کارشناسی",
                )
            )
        )
    }
}

