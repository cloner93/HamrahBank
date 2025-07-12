package com.pmb.profile.presentaion.update.status.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.profile.domain.entity.VersionEntity

data class UpdateStatusViewState(
    val loading: Boolean = false,
    val alert: AlertModelState? = null,
    val versionEntity: VersionEntity? = null,
) : BaseViewState {
    val enableUpdate: Boolean
        get() = !loading && versionEntity?.updated == false
}
