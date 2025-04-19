package com.pmb.transfer.presentation.transfer_amount.viewmodel

import com.pmb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransferAmountViewModel @Inject constructor() :
    BaseViewModel<TransferAmountViewActions, TransferAmountViewState, TransferAmountViewEvents>(
        TransferAmountViewState()
    ) {

    override fun handle(action: TransferAmountViewActions) {
        when (action) {
            is TransferAmountViewActions.UpdateAmount -> setState { it.copy(amount = action.amount) }
            is TransferAmountViewActions.SubmitAmount -> handleSubmitAmount(viewState.value.amount)
            TransferAmountViewActions.ClearAlert -> setState { it.copy(alertState = null) }
        }
    }

    private fun handleSubmitAmount(amount: Double) {
        postEvent(TransferAmountViewEvents.NavigateToDestinationType(amount))
    }
}