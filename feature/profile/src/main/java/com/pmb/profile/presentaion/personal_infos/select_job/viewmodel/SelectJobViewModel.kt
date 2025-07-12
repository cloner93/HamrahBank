package com.pmb.profile.presentaion.personal_infos.select_job.viewmodel

import com.pmb.core.platform.BaseViewModel
import com.pmb.profile.domain.entity.JobEntity
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import com.pmb.profile.presentaion.personal_infos.select_job.viewmodel.SelectJobViewEvents.NavigateBackToChangeJob
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SelectJobViewModel @Inject constructor() :
    BaseViewModel<SelectJobViewActions, SelectJobViewState, SelectJobViewEvents>(SelectJobViewState()) {
    private val _shareState = MutableStateFlow<PersonalInfoSharedState>(PersonalInfoSharedState())

    override fun handle(action: SelectJobViewActions) {
        when (action) {
            SelectJobViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is SelectJobViewActions.SelectJob -> handleSelectJob(action.jobEntity)
            is SelectJobViewActions.UpdateSearchQuery -> updateSearchQuery(action.query)
            is SelectJobViewActions.UpdateShareState -> updateShareState(action.sharedState)
        }
    }

    private fun handleSelectJob(job: JobEntity) {
        postEvent(NavigateBackToChangeJob(job))
    }

    private fun updateSearchQuery(query: String) {
        setState { it.copy(searchQuery = query) }
    }

    private fun updateShareState(shareState: PersonalInfoSharedState) {
        _shareState.value = shareState
        setState { it.copy(jobs = shareState.jobEntities) }
    }
}