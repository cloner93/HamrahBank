package com.pmb.profile.presentaion.update.latest.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.profile.domain.entity.VersionEntity

interface LatestVersionsViewActions : BaseViewAction {
    data object ClearAlert : LatestVersionsViewActions
    data class SelectVersion(val versionEntity: VersionEntity) : LatestVersionsViewActions
}