package com.pmb.auth.data.register.deposit_information

import com.pmb.auth.domain.register.deposit_information.entity.BranchCity
import com.pmb.auth.domain.register.deposit_information.entity.BranchCityEntity
import com.pmb.auth.domain.register.deposit_information.entity.BranchCityParams
import com.pmb.auth.domain.register.deposit_information.entity.BranchProvince
import com.pmb.auth.domain.register.deposit_information.entity.DepositInformationEntity
import com.pmb.auth.domain.register.deposit_information.entity.DepositType
import com.pmb.auth.domain.register.deposit_information.entity.SendDepositInformationEntity
import com.pmb.auth.domain.register.deposit_information.entity.SendDepositInformationParams
import com.pmb.auth.domain.register.deposit_information.repository.DepositInformationRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DepositInformationRepositoryImpl @Inject constructor() : DepositInformationRepository {
    override suspend fun getDepositInformation(): Flow<Result<DepositInformationEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        val depositType = listOf(
            DepositType(id = 0, type = "سپرده کوتاه مدت "),
            DepositType(id = 1, type = "سپرده کوتاه مدت قرض الحسنه"),
            DepositType(id = 0, type = "سپرده کوتاه مدت جاری"),
        )
        val branchProvince = listOf(
            BranchProvince(id = 0, province = "تهران"),
            BranchProvince(id = 1, province = "اصفهان"),
            BranchProvince(id = 2, province = "قزوین"),
        )

//        val annualIncomingPrediction = listOf(
//            AnnualIncomingPrediction(id = 0, income = "10000"),
//            AnnualIncomingPrediction(id = 1, income = "200000000"),
//            AnnualIncomingPrediction(id = 2, income = "5000000000")
//        )
        emit(
            Result.Success(
                DepositInformationEntity(
                    isSuccess = true,
                    depositType = depositType,
                    branchProvince = branchProvince,
                )
            )
        )
    }

    override suspend fun getBranchCity(params: BranchCityParams): Flow<Result<BranchCityEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            if (params.id == 0 || params.id == 1 || params.id == 2) {


                emit(
                    Result.Success(
                        BranchCityEntity(
                            isSuccess = true,
                            branchCity = when (params.id) {
                                0 -> {
                                    listOf(
                                        BranchCity(id = 0, city = "تهران"),
                                        BranchCity(id = 1, city = "اسلامشهر"),
                                        BranchCity(id = 2, city = "پرند")
                                    )
                                }

                                1 -> {
                                    listOf(
                                        BranchCity(id = 0, city = "اصفهان"),
                                        BranchCity(id = 1, city = "نطنز"),
                                        BranchCity(id = 2, city = "شاهین شهر")
                                    )
                                }

                                2 -> {
                                    listOf(
                                        BranchCity(id = 0, city = "قزوین"),
                                        BranchCity(id = 1, city = "تاکستان"),
                                        BranchCity(id = 2, city = "بویین زهرا")
                                    )
                                }

                                else -> {
                                    emptyList<BranchCity>()
                                }
                            }
                        )
                    )
                )
            } else {
                Result.Error(
                    message = "Your choice is not valid"
                )
            }
        }


    override suspend fun sendDepositInformation(params: SendDepositInformationParams): Flow<Result<SendDepositInformationEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            params.depositType.takeIf { it != null && it > 0 }?.let {
                emit(
                    Result.Success(
                        SendDepositInformationEntity(
                            isSuccess = true,
                        )
                    )
                )
            } ?: run {
                emit(Result.Error(message = "Your postal code is not valid"))
            }
        }
}