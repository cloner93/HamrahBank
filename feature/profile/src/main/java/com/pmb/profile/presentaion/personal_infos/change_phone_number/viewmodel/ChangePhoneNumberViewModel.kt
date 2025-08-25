package com.pmb.profile.presentaion.personal_infos.change_phone_number.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.profile.domain.use_case.ChangePhoneNumberUseCase
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePhoneNumberViewModel @Inject constructor(
    private val changePhoneNumberUseCase: ChangePhoneNumberUseCase
) : BaseViewModel<ChangePhoneNumberViewActions, ChangePhoneNumberViewState, ChangePhoneNumberViewEvents>(
    ChangePhoneNumberViewState()
) {
    override fun handle(action: ChangePhoneNumberViewActions) {
        when (action) {
            ChangePhoneNumberViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is ChangePhoneNumberViewActions.UpdateShareState -> handleUpdateShareState(action.sharedState)
            is ChangePhoneNumberViewActions.ChangePhoneNumber -> handleChangePhoneNumber(action.phoneNumber)
            ChangePhoneNumberViewActions.SubmitPhoneNumber -> updatePhoneNumber()
        }
    }

    private fun handleChangePhoneNumber(value: String) {
        setState { it.copy(phoneNumber = value) }
    }

    private fun handleUpdateShareState(sharedState: PersonalInfoSharedState) {
        setState { it.copy(phoneNumber = sharedState.phoneNumber.orEmpty()) }
    }

    private fun updatePhoneNumber() {
        viewModelScope.launch {
            changePhoneNumberUseCase.invoke(
                ChangePhoneNumberUseCase.Param(
                    userId = 10L,
                    phoneNumber = viewState.value.phoneNumber
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertState = null) }
                                    }
                                )
                            )
                        }
                    }

                    Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }

                    is Result.Success -> {
                        setState { it.copy(loading = false) }
                        postEvent(ChangePhoneNumberViewEvents.NavigateToVerifyPhoneNumber(result.data))
                    }
                }
            }
        }
    }
}