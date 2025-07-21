package com.pmb.transfer.presentation.transfer_amount.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.use_case.TransferAmountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferAmountViewModel @Inject constructor(
    private val transferAmountUseCase: TransferAmountUseCase
) :
    BaseViewModel<TransferAmountViewActions, TransferAmountViewState, TransferAmountViewEvents>(
        TransferAmountViewState()
    ) {
    private val _account = MutableStateFlow<TransactionClientBankEntity?>(null)

    override fun handle(action: TransferAmountViewActions) {
        when (action) {
            is TransferAmountViewActions.UpdateAmount -> setState { it.copy(amount = action.amount) }
            is TransferAmountViewActions.SubmitAmount -> handleSubmitAmount(viewState.value.amount)
            TransferAmountViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferAmountViewActions.UpdateDestination -> {
                _account.value = action.account
                setState { it.copy(accountInfo = action.account?.clientBankEntity) }
            }
        }
    }

    private fun handleSubmitAmount(amount: Double) {
        viewModelScope.launch {
            transferAmountUseCase.invoke(
                TransferAmountUseCase.Params(
                    amount = amount,
                    destination = when (_account.value!!.type) {
                        BankIdentifierNumberType.CARD -> _account.value?.clientBankEntity?.accountNumber
                        BankIdentifierNumberType.ACCOUNT -> _account.value?.clientBankEntity?.accountNumber
                        BankIdentifierNumberType.IBAN -> _account.value?.clientBankEntity?.iban
                    } ?: ""
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertState = null) }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Success -> {
                        setState { it.copy(loading = false) }
                        postEvent(
                            TransferAmountViewEvents.NavigateToDestinationType(
                                amount = amount,
                                methods = result.data
                            )
                        )
                    }

                    is Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }
                }
            }
        }
    }
}