package com.pmb.profile.presentaion.update.latest.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.profile.domain.entity.VersionEntity

data class LatestVersionsViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val versionEntities: List<VersionEntity> = mutableListOf(),
) : BaseViewState
