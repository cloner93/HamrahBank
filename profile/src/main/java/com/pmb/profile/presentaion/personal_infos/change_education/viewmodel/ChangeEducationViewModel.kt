package com.pmb.profile.presentaion.personal_infos.change_education.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.EducationEntity
import com.pmb.profile.domain.use_case.GetEducationsUseCase
import com.pmb.profile.domain.use_case.UpdateEducationUseCase
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeEducationViewModel @Inject constructor(
    private val educationsUseCase: GetEducationsUseCase,
    private val updateEducationUseCase: UpdateEducationUseCase,
) :
    BaseViewModel<ChangeEducationViewActions, ChangeEducationViewState, ChangeEducationViewEvents>(
        ChangeEducationViewState()
    ) {
    init {
        fetchEducations()
    }

    private val _shareState = MutableStateFlow<PersonalInfoSharedState>(PersonalInfoSharedState())

    override fun handle(action: ChangeEducationViewActions) {
        when (action) {
            ChangeEducationViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is ChangeEducationViewActions.ClickEducation -> changeEducation(action.education)
            ChangeEducationViewActions.SubmitEducation -> updateEducation()
            is ChangeEducationViewActions.UpdateShareState -> updateShareState(action.sharedState)
        }
    }

    private fun changeEducation(title: String) {
        val selectedEducation = viewState.value.educationEntities.find { it.title == title }
        selectedEducation?.let {
            setState { it.copy(
                enableButton = selectedEducation.id != (_shareState.value.educationEntity?.id
                    ?: -1),
                educationEntity = selectedEducation
            ) }
        }
    }

    private fun updateShareState(sharedState: PersonalInfoSharedState) {
        _shareState.value = sharedState
        setState {
            it.copy(
                educationEntity = sharedState.educationEntity ?: EducationEntity.createEmpty()
            )
        }
    }

    private fun fetchEducations() {
        viewModelScope.launch {
            educationsUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                    },
                                    onDismissed = {
                                    })
                            )
                        }
                    }

                    Result.Loading -> Unit

                    is Result.Success -> {
                        setState {
                            it.copy(educationEntities = result.data)
                        }
                    }
                }
            }
        }
    }

    private fun updateEducation() {
        viewModelScope.launch {
            updateEducationUseCase.invoke(
                UpdateEducationUseCase.Param(
                    id = viewState.value.educationEntity.id,
                    job = viewState.value.educationEntity.title
                )
            ).collect { result ->
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
                                    })
                            )
                        }
                    }

                    Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }

                    is Result.Success -> {
                        setState { it.copy(loading = false) }
                        postEvent(ChangeEducationViewEvents.NavigateBackToPersonalInfo(result.data))
                    }
                }
            }
        }
    }
}