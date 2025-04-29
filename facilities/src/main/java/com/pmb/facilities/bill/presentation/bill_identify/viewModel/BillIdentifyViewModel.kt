package com.pmb.facilities.bill.presentation.bill_identify.viewModel

import com.pmb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BillIdentifyViewModel @Inject constructor(
    initialState: BillIdentifyViewState
) : BaseViewModel<BillIdentifyViewActions, BillIdentifyViewState, BillIdentifyViewEvents>(
    initialState
) {
    override fun handle(action: BillIdentifyViewActions) {
        when (action) {
            is BillIdentifyViewActions.ClearAlertModelState -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}