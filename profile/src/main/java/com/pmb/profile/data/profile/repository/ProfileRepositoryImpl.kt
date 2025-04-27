package com.pmb.profile.data.profile.repository

import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.AddressEntity
import com.pmb.profile.domain.entity.JobEntity
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

    override suspend fun changeAddress(
        id: Long,
        postalCode: String
    ): Flow<Result<AddressEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(
            Result.Success(mockAddressEntity.copy(postalCode = postalCode))
        )
    }

    override suspend fun fetchJobs(): Flow<Result<List<JobEntity>>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(Result.Success(mockJobEntities))
    }

    override suspend fun updateJob(
        id: Long,
        title: String
    ): Flow<Result<JobEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(Result.Success(JobEntity(id, title)))
    }
}

private val mockJobEntities = listOf(
    JobEntity(1, "برنامه‌نویس"),
    JobEntity(2, "پزشک"),
    JobEntity(3, "پرستار"),
    JobEntity(4, "مهندس عمران"),
    JobEntity(5, "معلم"),
    JobEntity(6, "راننده تاکسی"),
    JobEntity(7, "کارگر ساختمانی"),
    JobEntity(8, "مدیر فروش"),
    JobEntity(9, "طراح گرافیک"),
    JobEntity(10, "آشپز"),
    JobEntity(11, "نقاش ساختمان"),
    JobEntity(12, "مکانیک خودرو"),
    JobEntity(13, "آرایشگر"),
    JobEntity(14, "خیاط"),
    JobEntity(15, "وکیل"),
    JobEntity(16, "حسابدار"),
    JobEntity(17, "مشاور املاک"),
    JobEntity(18, "فروشنده"),
    JobEntity(19, "کشاورز"),
    JobEntity(20, "نصاب کولر و گاز"),
    JobEntity(21, "لوله‌کش"),
    JobEntity(22, "تعمیرکار لوازم خانگی"),
    JobEntity(23, "برق‌کار"),
    JobEntity(24, "مربی ورزشی"),
    JobEntity(25, "عکاس"),
    JobEntity(26, "کارشناس بیمه"),
    JobEntity(27, "کارشناس سئو"),
    JobEntity(28, "دیجیتال مارکتر"),
    JobEntity(29, "نویسنده محتوا"),
    JobEntity(30, "پیک موتوری")
)


private val mockPersonalInfoEntity
    get() = PersonalInfoEntity(
        username = "pooriak",
        phoneNumber = "09123456789",
        addressEntity = AddressEntity(
            id = 12345L,
            address = "تهران، کوی نصر، خیابان ۲۷، پلاکfif، واحد ۳",
            postalCode = "1234567890"
        ),
        education = "دانشجو",
        jobEntity = JobEntity(
            id = 10,
            title =  "کارشناسی"
        ),
    )

private val mockOtpEntity
    get() = OtpEntity(
        id = 12873942,
        duration = 30,
        phoneNumber = "09123456789",
    )

private val mockAddressEntity
    get() = AddressEntity(
        id = 12345L,
        address = "آدرس فعلی: تهران، کوی نصر، خیابان ۲۷، پلاک ۱۵، واحد ۳",
        postalCode = "1234567890"
    )

