package com.pmb.profile.presentaion.update.status.viewmodel

import com.pmb.core.platform.BaseViewAction

interface UpdateStatusViewActions: BaseViewAction {
    data object ClearAlert : UpdateStatusViewActions
    data object UpdateClicked : UpdateStatusViewActions
    data object ShowNewChanges : UpdateStatusViewActions
    data object LatestVersion : UpdateStatusViewActions
}