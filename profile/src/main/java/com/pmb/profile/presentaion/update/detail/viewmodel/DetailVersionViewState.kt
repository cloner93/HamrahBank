package com.pmb.profile.presentaion.update.detail.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.profile.domain.entity.VersionEntity

data class DetailVersionViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val versionEntity: VersionEntity? = null,
) : BaseViewState
