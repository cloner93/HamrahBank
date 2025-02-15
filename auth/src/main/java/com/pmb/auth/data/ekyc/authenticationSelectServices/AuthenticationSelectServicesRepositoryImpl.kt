package com.pmb.auth.data.ekyc.authenticationSelectServices

import androidx.compose.runtime.mutableStateOf
import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.ConfirmSelectServicesEntity
import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.SelectServicesEntity
import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.SelectServicesObject
import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.SelectServicesParams
import com.pmb.auth.domain.ekyc.authenticationSelectServices.repository.AuthenticationSelectServicesRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationSelectServicesRepositoryImpl @Inject constructor(

) : AuthenticationSelectServicesRepository {
    override fun getServices(): Flow<Result<SelectServicesEntity>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(
            Result.Success(
                SelectServicesEntity(
                    isSuccess = true,
                    selectServicesList = mutableListOf(
                        SelectServicesObject(
                            id = 0,
                            " فعالسازی اینترنت بانک",
                            price = 20000.0,
                            isChecked = mutableStateOf(false)
                        ),
                        SelectServicesObject(
                            id = 1,
                            " دریافت کارت بانکی",
                            price = 20000.0,
                            isChecked = mutableStateOf(false)
                        ),
                    )
                )
            )
        )
    }

    override fun confirmServices(params: SelectServicesParams): Flow<Result<ConfirmSelectServicesEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            params.takeIf { it.ids.size == 2 }?.let {
                emit(Result.Success(ConfirmSelectServicesEntity(isSuccess = true)))
            } ?: run {
                emit(Result.Error(message = "You should choose both of them"))
            }
        }
}