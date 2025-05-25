package com.pmb.profile.presentaion.update.detail.viewmodel

import com.pmb.core.platform.BaseViewModel
import com.pmb.profile.domain.entity.VersionEntity
import javax.inject.Inject

class DetailVersionViewModel @Inject constructor() :
    BaseViewModel<DetailVersionViewActions, DetailVersionViewState, DetailVersionViewEvents>
        (DetailVersionViewState()) {

    override fun handle(action: DetailVersionViewActions) {
        when (action) {
            DetailVersionViewActions.ClearAlert -> setState { it.copy(alertState = null) }
        }
    }


    fun setVersion(versionEntity: VersionEntity) {
        setState { it.copy(versionEntity = versionEntity) }
    }
}