package com.pmb.auth.presentation.register.account_opening.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.opening_account.entity.OpeningAccountParams
import com.pmb.auth.domain.register.opening_account.useCase.SendOpeningAccountUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.persiancalendar.calendar.PersianDate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpeningAccountViewModel @Inject constructor(
    private val sendOpeningAccountUseCase: SendOpeningAccountUseCase
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
                handleSendOpeningAccountData(
                    action.phoneNumber,
                    action.nationalId,
                    action.birthDay
                )
            }
        }
    }

    private fun handleSendOpeningAccountData(
        phoneNumber: String,
        nationalId: String,
        birthDay: String
    ) {
        viewModelScope.launch {
            sendOpeningAccountUseCase.invoke(
                OpeningAccountParams(
                    phoneNumber,
                    nationalId,
                    birthDay
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