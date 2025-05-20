package com.pmb.profile.presentaion.update.latest.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.profile.domain.entity.VersionEntity

interface LatestVersionsViewEvents : BaseViewEvent {
    data class NavigateToDetail(val versionEntity: VersionEntity) : LatestVersionsViewEvents
}