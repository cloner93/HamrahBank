package com.pmb.profile.data.profile.repository

import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.OtpEntity
import com.pmb.profile.domain.entity.PersonalInfoEntity
import com.pmb.profile.domain.repository.ProfileRepository
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

    override suspend fun fetchPersonalInfo(params: PersonalInfoUseCase.Param): Flow<Result<PersonalInfoEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(mockPersonalInfoEntity))
        }

    override suspend fun changeUsername(
        userId: Long, username: String
    ): Flow<Result<PersonalInfoEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(
            Result.Success(
                mockPersonalInfoEntity.copy(username = username)
            )
        )
    }

    override suspend fun changePhoneNumber(
        userId: Long, phoneNumber: String
    ): Flow<Result<OtpEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(
            Result.Success(mockOtpEntity.copy(phoneNumber = phoneNumber))
        )
    }

    override suspend fun resendOtpCode(
        id: Long,
        phoneNumber: String
    ): Flow<Result<OtpEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(Result.Success(mockOtpEntity))
    }

    override suspend fun submitOtpCode(
        otpId: Long,
        phoneNumber: String,
        otpCode: String
    ): Flow<Result<PersonalInfoEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(
            Result.Success(
                mockPersonalInfoEntity.copy(phoneNumber = phoneNumber)
            )
        )
    }
}


private val mockPersonalInfoEntity
    get() = PersonalInfoEntity(
        username = "pooriak",
        phoneNumber = "09123456789",
        address = "تهران، کوی نصر، خیابانکوی نصر",
        education = "دانشجو",
        job = "کارشناسی",
    )

private val mockOtpEntity
    get() = OtpEntity(
        id = 12873942,
        duration = 30,
        phoneNumber = "09123456789",
    )

