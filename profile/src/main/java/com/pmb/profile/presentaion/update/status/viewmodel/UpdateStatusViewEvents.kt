package com.pmb.profile.presentaion.update.status.viewmodel

import com.pmb.core.platform.BaseViewEvent

interface UpdateStatusViewEvents : BaseViewEvent {
    data object NavigateToUpdateHistory : UpdateStatusViewEvents
}