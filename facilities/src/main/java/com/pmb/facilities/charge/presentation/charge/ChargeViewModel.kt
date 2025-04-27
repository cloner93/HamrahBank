package com.pmb.facilities.charge.presentation.charge

import com.pmb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChargeViewModel @Inject constructor(
    initialState: ChargeViewState
) :
    BaseViewModel<ChargeViewActions, ChargeViewState, ChargeViewEvents>(initialState) {

    override fun handle(action: ChargeViewActions) {

    }
}