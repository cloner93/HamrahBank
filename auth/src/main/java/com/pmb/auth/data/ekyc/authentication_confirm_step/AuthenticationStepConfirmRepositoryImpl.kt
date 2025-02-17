package com.pmb.auth.data.ekyc.authentication_confirm_step

import com.pmb.auth.domain.ekyc.authentication_confirm_step.entity.AuthenticationStepConfirmEntity
import com.pmb.auth.domain.ekyc.authentication_confirm_step.entity.AuthenticationStepConfirmObject
import com.pmb.auth.domain.ekyc.authentication_confirm_step.repository.AuthenticationConfirmStepRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationStepConfirmRepositoryImpl @Inject constructor() :
    AuthenticationConfirmStepRepository {
    override fun getAuthenticationStepConfirmData(): Flow<Result<AuthenticationStepConfirmEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(
                Result.Success(
                    AuthenticationStepConfirmEntity(
                        isSuccess = true,
                        authenticationStepConfirmList = listOf(
                            AuthenticationStepConfirmObject(
                                title = "ثبت اطلاعات و مدارک مورد نیاز",
                                isEnabled = true

                            ),
                            AuthenticationStepConfirmObject(
                                title = "احراز هویت",
                                isEnabled = true
                            ),
                            AuthenticationStepConfirmObject(
                                title = "انتخاب خدمات",
                                isEnabled = false
                            ),
                            AuthenticationStepConfirmObject(
                                title = "تایید قوانین و افتتاح حساب",
                                isEnabled = false
                            ),
                        )
                    )
                )
            )
        }


}