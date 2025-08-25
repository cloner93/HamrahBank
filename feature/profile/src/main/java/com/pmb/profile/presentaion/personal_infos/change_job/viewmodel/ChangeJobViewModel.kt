package com.pmb.profile.presentaion.personal_infos.change_job.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.JobEntity
import com.pmb.profile.domain.use_case.JobsUseCase
import com.pmb.profile.domain.use_case.UpdateJobUseCase
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeJobViewModel @Inject constructor(
    private val jobsUseCase: JobsUseCase,
    private val updateJobUseCase: UpdateJobUseCase,
) :
    BaseViewModel<ChangeJobViewActions, ChangeJobViewState, ChangeJobViewEvents>(ChangeJobViewState()) {
    private val _shareState = MutableStateFlow<PersonalInfoSharedState>(PersonalInfoSharedState())
    val shareState: StateFlow<PersonalInfoSharedState> = _shareState


    override fun handle(action: ChangeJobViewActions) {
        when (action) {
            ChangeJobViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            ChangeJobViewActions.ClickJob -> checkExistJobs()
            ChangeJobViewActions.SubmitJob -> updateJob()
            is ChangeJobViewActions.UpdateShareState -> updateShareState(action.sharedState)
        }
    }

    private fun checkExistJobs() {
        if (_shareState.value.jobEntities.isNotEmpty())
            postEvent(ChangeJobViewEvents.NavigateToListJob(_shareState.value.jobEntities))
        else fetchJobs()
    }

    private fun updateShareState(sharedState: PersonalInfoSharedState) {
        _shareState.value = sharedState
        val selectedJob = viewState.value.jobEntity.id != -1L && (sharedState.queueJob
            ?: JobEntity.createEmpty()) != viewState.value.jobEntity
        setState {
            it.copy(
                selectedJob = selectedJob,
                jobEntity = sharedState.queueJob ?: sharedState.jobEntity ?: JobEntity.createEmpty()
            )
        }
    }

    private fun updateJob() {
        viewModelScope.launch {
            updateJobUseCase.invoke(
                UpdateJobUseCase.Param(
                    id = shareState.value.queueJob?.id ?: -1,
                    job = shareState.value.queueJob?.title ?: ""
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
                        postEvent(ChangeJobViewEvents.NavigateBackToPersonalInfo(result.data))
                    }
                }
            }
        }
    }

    private fun fetchJobs() {
        viewModelScope.launch {
            jobsUseCase.invoke(Unit).collect { result ->
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
                        _shareState.value = _shareState.value.copy(jobEntities = result.data)
                        postEvent(ChangeJobViewEvents.NavigateToListJob(result.data))
                    }
                }
            }
        }
    }

}