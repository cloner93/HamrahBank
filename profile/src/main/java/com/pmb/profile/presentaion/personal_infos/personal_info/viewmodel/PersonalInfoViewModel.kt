package com.pmb.profile.presentaion.personal_infos.personal_info.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.PersonalInfoEntity
import com.pmb.profile.domain.use_case.PersonalInfoUseCase
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalInfoViewModel @Inject constructor(
    private val personalInfoUseCase: PersonalInfoUseCase
) : BaseViewModel<PersonalInfoViewActions, PersonalInfoViewState, PersonalInfoViewEvents>(
    PersonalInfoViewState()
) {

    init {
        fetchPersonalInfo()
    }

    override fun handle(action: PersonalInfoViewActions) {
        when (action) {
            is PersonalInfoViewActions.UpdateShareState -> handleUpdateShareState(action.sharedState)
            PersonalInfoViewActions.ChangeUsername -> postEvent(PersonalInfoViewEvents.NavigateToChangeUsername)
            PersonalInfoViewActions.ChangePhoneNumber -> postEvent(PersonalInfoViewEvents.NavigateToChangePhoneNumber)
            PersonalInfoViewActions.ChangeAddress -> postEvent(PersonalInfoViewEvents.NavigateToChangeAddress)
            PersonalInfoViewActions.ChangeEducation -> postEvent(PersonalInfoViewEvents.NavigateToChangeEducation)
            PersonalInfoViewActions.ChangeJob -> postEvent(PersonalInfoViewEvents.NavigateToChangeJob)
            PersonalInfoViewActions.ClearAlert -> setState { it.copy(alertState = null) }
        }
    }

    private fun handleUpdateShareState(shareState: PersonalInfoSharedState) {
        setState {
            it.copy(
                personalInfo = PersonalInfoEntity(
                    username = shareState.username,
                    phoneNumber = shareState.phoneNumber,
                    address = shareState.address,
                    education = shareState.education,
                    job = shareState.job
                )
            )
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
                                    }))
                        }
                    }

                    Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false, personalInfo = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}