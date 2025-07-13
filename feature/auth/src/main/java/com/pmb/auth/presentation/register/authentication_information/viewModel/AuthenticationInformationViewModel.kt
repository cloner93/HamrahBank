package com.pmb.auth.presentation.register.authentication_information.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.authentication_information.entity.SendAuthenticationInformationParam
import com.pmb.auth.domain.register.authentication_information.useCase.GetAuthenticationInformationUseCase
import com.pmb.auth.domain.register.authentication_information.useCase.SendAuthenticationInformationUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationInformationViewModel @Inject constructor(
    initialState: AuthenticationInformationViewState,
    private val getAuthenticationInformationUseCase: GetAuthenticationInformationUseCase,
    private val sendAuthenticationInformationUseCase: SendAuthenticationInformationUseCase
) : BaseViewModel<AuthenticationInformationViewActions, AuthenticationInformationViewState, AuthenticationInformationViewEvents>(
    initialState
) {
    override fun handle(action: AuthenticationInformationViewActions) {
        when (action) {
            AuthenticationInformationViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            AuthenticationInformationViewActions.GetAuthenticationEntity -> {
                handleGetAuthenticationInfo()
            }

            is AuthenticationInformationViewActions.SendAuthenticationParams -> {
                handleSendAuthenticationParams(action)
            }

            is AuthenticationInformationViewActions.SetCityId -> {
                handleSetCityId(action)
            }

            is AuthenticationInformationViewActions.SetIdentifyPlaceId -> {
                handleIdentifyPlaceId(action)
            }

            is AuthenticationInformationViewActions.SetEducation -> {
                handleSetEducation(action)
            }

            is AuthenticationInformationViewActions.SetIdentifyDay -> {
                handleSetIdentifyDay(action)
            }

            is AuthenticationInformationViewActions.SetPhoneNumber -> {
                handleSetPhoneNumber(action)
            }

            is AuthenticationInformationViewActions.SetIdentifyArea -> {
                handleSetIdentifyArea(action)
            }
        }
    }

    private fun handleSetCityId(action: AuthenticationInformationViewActions.SetCityId) {
        viewState.value.originalAuthenticationInformation?.cities?.findLast { it.city == action.city }?.id?.let { id ->
            setState {
                it.copy(
                    sendAuthenticationInformationParam = it.sendAuthenticationInformationParam?.copy(
                        cityId = id
                    ) ?: run {
                        SendAuthenticationInformationParam(
                            cityId = id,
                            identifyId = null,
                            birthDate = null,
                            identifyArea = null,
                            phoneNumber = null,
                            educationId = null


                        )
                    }
                )
            }
        }
    }

    private fun handleSetIdentifyDay(action: AuthenticationInformationViewActions.SetIdentifyDay) {
        setState {
            it.copy(
                sendAuthenticationInformationParam = it.sendAuthenticationInformationParam?.copy(
                    birthDate = action.identifyDay
                ) ?: run {
                    SendAuthenticationInformationParam(
                        cityId = null,
                        identifyId = null,
                        birthDate = action.identifyDay,
                        identifyArea = null,
                        phoneNumber = null,
                        educationId = null
                    )
                }
            )
        }
    }

    private fun handleSetIdentifyArea(action: AuthenticationInformationViewActions.SetIdentifyArea) {
        setState {
            it.copy(
                sendAuthenticationInformationParam = it.sendAuthenticationInformationParam?.copy(
                    identifyArea = action.identifyArea
                ) ?: run {
                    SendAuthenticationInformationParam(
                        cityId = null,
                        identifyId = null,
                        birthDate = null,
                        identifyArea = action.identifyArea,
                        phoneNumber = null,
                        educationId = null
                    )
                }
            )
        }
    }

    private fun handleSetPhoneNumber(action: AuthenticationInformationViewActions.SetPhoneNumber) {
        setState {
            it.copy(
                sendAuthenticationInformationParam = it.sendAuthenticationInformationParam?.copy(
                    identifyArea = action.phoneNumber
                ) ?: run {
                    SendAuthenticationInformationParam(
                        cityId = null,
                        identifyId = null,
                        birthDate = null,
                        identifyArea = null,
                        phoneNumber = action.phoneNumber,
                        educationId = null
                    )
                }
            )
        }
    }

    private fun handleIdentifyPlaceId(action: AuthenticationInformationViewActions.SetIdentifyPlaceId) {
        viewState.value.originalAuthenticationInformation?.identifyPlace?.findLast { it.city == action.city }?.id?.let { id ->
            setState {
                it.copy(
                    sendAuthenticationInformationParam = it.sendAuthenticationInformationParam?.copy(
                        identifyId = id
                    ) ?: run {
                        SendAuthenticationInformationParam(
                            identifyId = id,
                            cityId = null,
                            birthDate = null,
                            identifyArea = null,
                            phoneNumber = null,
                            educationId = null


                        )
                    }
                )
            }
        }
    }

    private fun handleSetEducation(action: AuthenticationInformationViewActions.SetEducation) {
        viewState.value.originalAuthenticationInformation?.educations?.findLast { it.education == action.eduction }?.id?.let { id ->
            setState {
                it.copy(
                    sendAuthenticationInformationParam = it.sendAuthenticationInformationParam?.copy(
                        educationId = id
                    ) ?: run {
                        SendAuthenticationInformationParam(
                            educationId = id,
                            identifyId = null,
                            birthDate = null,
                            identifyArea = null,
                            phoneNumber = null,
                            cityId = null


                        )
                    }
                )
            }
        }
    }

    private fun handleSendAuthenticationParams(action: AuthenticationInformationViewActions.SendAuthenticationParams) {
        viewModelScope.launch {
            sendAuthenticationInformationUseCase.invoke(
                SendAuthenticationInformationParam(
                    cityId = action.cityId,
                    identifyArea = action.identifyArea,
                    birthDate = action.birthDate,
                    phoneNumber = action.phoneNumber,
                    educationId = action.educationId,
                    identifyId = action.identifyId
                )
            ).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false, alertModelState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                            )
                        }
                        postEvent(AuthenticationInformationViewEvents.SendAuthenticationInformationViewSucceed)
                    }
                }
            }
        }

    }

    private fun handleGetAuthenticationInfo() {
        viewModelScope.launch {
            getAuthenticationInformationUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false, alertModelState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                authenticationInformation = result.data,
                                originalAuthenticationInformation = result.data
                            )
                        }
                    }
                }

            }
        }
    }

    init {
        handle(AuthenticationInformationViewActions.GetAuthenticationEntity)
    }
}