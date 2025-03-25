package com.pmb.auth.data.register.authentication_information

import com.pmb.auth.domain.register.authentication_information.entity.City
import com.pmb.auth.domain.register.authentication_information.entity.Education
import com.pmb.auth.domain.register.authentication_information.entity.GetAuthenticationEntity
import com.pmb.auth.domain.register.authentication_information.entity.SendAuthenticationInformationParam
import com.pmb.auth.domain.register.authentication_information.repository.AuthenticationInformationRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationInformationRepositoryImpl @Inject constructor() :
    AuthenticationInformationRepository {
    override suspend fun getAuthenticationInformation(): Flow<Result<GetAuthenticationEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            val cities = listOf(
                City(id = 0, city = "آبادان"),
                City(id = 0, city = "اصفهان"),
                City(id = 0, city = "تبریز"),
                City(id = 0, city = "تهران"),
                City(id = 0, city = "بندرعباس"),
                City(id = 0, city = "بوشهر"),
                City(id = 0, city = "مشهد"),
            )
            val identifyPlaces = listOf(
                City(id = 0, city = "آبادان"),
                City(id = 0, city = "اصفهان"),
                City(id = 0, city = "تبریز"),
                City(id = 0, city = "تهران"),
                City(id = 0, city = "بندرعباس"),
                City(id = 0, city = "بوشهر"),
                City(id = 0, city = "مشهد"),
            )
            val educations = listOf(
                Education(id = 0, education = "زیر دیپلم"),
                Education(id = 1, education = "دیپلم"),
                Education(id = 2, education = "فوق دیپلم"),
                Education(id = 3, education = "لیسانس"),
                Education(id = 4, education = "فوق لیسانس"),
                Education(id = 5, education = "دکترا"),
            )
            emit(
                Result.Success(
                    GetAuthenticationEntity(
                        isSuccess = true,
                        cities = cities,
                        identifyPlace = identifyPlaces,
                        educations = educations,
                    )
                )
            )
        }

    override suspend fun sendAuthenticationInformation(authenticationInformationParam: SendAuthenticationInformationParam): Flow<Result<Boolean>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(Result.Success(true))
        }

}