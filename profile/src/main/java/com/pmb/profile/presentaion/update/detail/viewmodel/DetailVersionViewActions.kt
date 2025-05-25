package com.pmb.profile.presentaion.update.detail.viewmodel

import com.pmb.core.platform.BaseViewAction

sealed interface DetailVersionViewActions : BaseViewAction {
    data object ClearAlert : DetailVersionViewActions
}