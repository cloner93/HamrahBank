package com.pmb.profile.domain.repository

import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.AddressEntity
import com.pmb.profile.domain.entity.EducationEntity
import com.pmb.profile.domain.entity.JobEntity
import com.pmb.profile.domain.entity.OtpEntity
import com.pmb.profile.domain.entity.PersonalInfoEntity
import com.pmb.profile.domain.entity.VersionEntity
import com.pmb.profile.domain.use_case.PersonalInfoUseCase
import com.pmb.profile.domain.use_case.SubmitCommentsSuggestionsUseCase
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun logOut(): Flow<Result<String>>
    suspend fun fetchPersonalInfo(params: PersonalInfoUseCase.Param): Flow<Result<PersonalInfoEntity>>
    suspend fun changeUsername(userId: Long, username: String): Flow<Result<PersonalInfoEntity>>
    suspend fun changePhoneNumber(
        userId: Long,
        phoneNumber: String
    ): Flow<Result<OtpEntity>>

    suspend fun resendOtpCode(
        id: Long,
        phoneNumber: String
    ): Flow<Result<OtpEntity>>

    suspend fun submitOtpCode(
        otpId: Long,
        phoneNumber: String,
        otpCode: String
    ): Flow<Result<PersonalInfoEntity>>

    suspend fun changeAddress(id: Long, postalCode: String): Flow<Result<AddressEntity>>
    suspend fun fetchJobs(): Flow<Result<List<JobEntity>>>
    suspend fun updateJob(id: Long, title: String): Flow<Result<JobEntity>>
    suspend fun fetchEducations(): Flow<Result<List<EducationEntity>>>
    suspend fun updateEducation(id: Long, title: String): Flow<Result<EducationEntity>>
    suspend fun checkUpdate(): Flow<Result<VersionEntity>>
    suspend fun updateVersion(): Flow<Result<VersionEntity>>
    suspend fun fetchVersions(): Flow<Result<List<VersionEntity>>>
    suspend fun changePassword(
        userId: String,
        oldPassword: String,
        newPassword: String
    ): Flow<Result<PersonalInfoEntity>>

    suspend fun submitCommentsSuggestions(params: SubmitCommentsSuggestionsUseCase.Params): Flow<Result<Unit>>
}