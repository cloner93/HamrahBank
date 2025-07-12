package com.pmb.auth.data.register.select_job_information

import com.pmb.auth.domain.register.select_job_information.entity.JobInformation
import com.pmb.auth.domain.register.select_job_information.entity.SelectJobInformationEntity
import com.pmb.auth.domain.register.select_job_information.repository.SelectJobInformationRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SelectJobInformationRepositoryImpl @Inject constructor() : SelectJobInformationRepository {
    override suspend fun getJobInformation(): Flow<Result<SelectJobInformationEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(
            Result.Success(
                SelectJobInformationEntity(
                    isSuccess = true,
                    selectJobInformation = listOf(
                        JobInformation(
                            id = 0,
                            jobInformation = "صادر کننده / وارد کننده (بازرگان)"
                        ),
                        JobInformation(id = 1, jobInformation = "عمده فروش"),
                        JobInformation(
                            id = 2,
                            jobInformation = "تکنسین پزشکی / کارشناس فنی مسائل پزشکی"
                        ),
                        JobInformation(
                            id = 3,
                            jobInformation = "طراح و تحلیلگر علمی / فنی / پژوهشگر"
                        ),
                        JobInformation(
                            id = 4,
                            jobInformation = "فروشنده اشیا گرانبها (فرش، عتیقه و ...)"
                        ),
                        JobInformation(
                            id = 5,
                            jobInformation = "هنرمند (فیلمساز، نقاش، مجسمه ساز و ...)"
                        ),
                        JobInformation(id = 6, jobInformation = "ورزشکار حرفه ای / خبرنگار"),
                        JobInformation(id = 7, jobInformation = "رییس / مدیر عامل / معاون"),
                        JobInformation(id = 8, jobInformation = "صراف"),
                    )
                )
            )
        )
    }
}