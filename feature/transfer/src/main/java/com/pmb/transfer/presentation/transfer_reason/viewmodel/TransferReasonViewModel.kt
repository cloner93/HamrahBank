package com.pmb.transfer.presentation.transfer_reason.viewmodel

import com.pmb.core.platform.BaseViewModel
import com.pmb.transfer.presentation.transfer_reason.viewmodel.TransferReasonViewEvents.NavigateUp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransferReasonViewModel @Inject constructor() :
    BaseViewModel<TransferReasonViewActions, TransferReasonViewState, TransferReasonViewEvents>(
        TransferReasonViewState()
    ) {

    override fun handle(action: TransferReasonViewActions) {
        when (action) {
            TransferReasonViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferReasonViewActions.SelectReason -> {
                setState { it.copy(selectedReason = action.reason) }
                postEvent(NavigateUp(action.reason))
            }

            is TransferReasonViewActions.UpdateReasons -> setState {
                it.copy(
                    reasons = action.reasons
                )
            }
        }
    }
}