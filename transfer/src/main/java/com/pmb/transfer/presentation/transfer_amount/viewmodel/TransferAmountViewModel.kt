package com.pmb.transfer.presentation.transfer_amount.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.pmb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransferAmountViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel<TransferAmountViewActions, TransferAmountViewState, TransferAmountViewEvents>(
        TransferAmountViewState()
    ) {

    override fun handle(action: TransferAmountViewActions) {
        when (action) {
            is TransferAmountViewActions.SubmitAmount -> handleSubmitAmount(action.amount)
            TransferAmountViewActions.ClearAlert -> setState { it.copy(alertState = null) }
        }
    }

    private fun handleSubmitAmount(amount: Long) {
        postEvent(TransferAmountViewEvents.NavigateToDestinationType(amount))
    }
}