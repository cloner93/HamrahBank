package com.pmb.auth.presentation.register.account_opening.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.openAccount.GenerateCodeParams
import com.pmb.domain.usecae.auth.openAccount.GenerateCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.persiancalendar.calendar.PersianDate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpeningAccountViewModel @Inject constructor(
    private val generateCodeUseCase: GenerateCodeUseCase
) :
    BaseViewModel<OpeningAccountViewActions, OpeningAccountViewState, OpeningAccountViewEvents>(
        OpeningAccountViewState()
    ) {
    override fun handle(action: OpeningAccountViewActions) {
        when (action) {
            is OpeningAccountViewActions.ClearAlertModelState -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is OpeningAccountViewActions.SetBirthday -> {
                handleSetBirthday(action.birthDay)
            }

            is OpeningAccountViewActions.SetNationalId -> {
                handleSetNationalId(action.nationalId)
            }

            is OpeningAccountViewActions.SetPhoneNumber -> {
                handleSetPhoneNumber(action.phoneNumber)
            }

            is OpeningAccountViewActions.ShowBottomSheet -> {
                handleShowBottomSheet(action.isVisible)
            }

            is OpeningAccountViewActions.SendOpeningAccountData -> {
                handleSendOpeningAccountData()
            }
        }
    }

    private fun handleSendOpeningAccountData(

    ) {
        viewModelScope.launch {
            generateCodeUseCase.invoke(
                GenerateCodeParams(
                    nationalCode = viewState.value.phoneNumber ?: "",
                    mobileNo = viewState.value.phoneNumber ?: "",
                    birthDate = viewState.value.birthDay?.let { "${it.year}${it.month}${it.dayOfMonth}" }
                        ?: run { "" }
                )
            ).collectLatest { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false
                            )
                        }
                        postEvent(OpeningAccountViewEvents.SendOpeningAccountViewSucceed)
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                alertModelState = AlertModelState.Dialog(
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
                }
            }
        }
    }

    private fun handleShowBottomSheet(isVisible: Boolean) {
        setState {
            it.copy(
                isShowingBottomSheet = isVisible
            )
        }
    }

    private fun handleSetPhoneNumber(phoneNumber: String) {
        setState {
            it.copy(
                phoneNumber = phoneNumber
            )
        }
    }

    private fun handleSetNationalId(nationalId: String) {
        setState {
            it.copy(
                nationalId = nationalId
            )
        }
    }

    private fun handleSetBirthday(date: PersianDate) {
        setState {
            it.copy(
                birthDay = date
            )
        }
    }

}