package com.pmb.profile.presentaion.update.status.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.profile.domain.entity.VersionEntity

sealed interface UpdateStatusViewEvents : BaseViewEvent {
    data object NavigateToUpdateHistory : UpdateStatusViewEvents
    data class NavigateToDetail(val currentVersion: VersionEntity) : UpdateStatusViewEvents
}