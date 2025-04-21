package com.pmb.profile.presentaion.personal_info.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.profile.domain.use_case.PersonalInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalInfoViewModel @Inject constructor(
    private val personalInfoUseCase: PersonalInfoUseCase
) :
    BaseViewModel<PersonalInfoViewActions, PersonalInfoViewState, PersonalInfoViewEvents>(
        PersonalInfoViewState()
    ) {

    init {
        fetchPersonalInfo()
    }

    override fun handle(action: PersonalInfoViewActions) {
        when (action) {
            PersonalInfoViewActions.ChangeAddress -> postEvent(PersonalInfoViewEvents.NavigateToChangeAddress)
            PersonalInfoViewActions.ChangeEducation -> postEvent(PersonalInfoViewEvents.NavigateToChangeEducation)
            PersonalInfoViewActions.ChangeJob -> postEvent(PersonalInfoViewEvents.NavigateToChangeJob)
            PersonalInfoViewActions.ChangePhoneNumber -> postEvent(PersonalInfoViewEvents.NavigateToChangePhoneNumber)
            PersonalInfoViewActions.ChangeUsername -> postEvent(PersonalInfoViewEvents.NavigateToChangeUsername)
            PersonalInfoViewActions.ClearAlert -> setState { it.copy(alertState = null) }
        }
    }

    private fun fetchPersonalInfo() {
        viewModelScope.launch {
            personalInfoUseCase.invoke(PersonalInfoUseCase.Param(userId = 10L)).collect { result ->
                when (result) {

                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { it.copy(loading = false) }
                                    },
                                    onDismissed = {
                                        setState { it.copy(loading = false) }
                                    }
                                )
                            )
                        }
                    }

                    Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                personalInfo = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}