package com.pmb.transfer.presentation.transfer_destination_input.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.param.AccountDetailParam
import com.pmb.transfer.domain.use_case.AccountDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferDestinationInputViewModel @Inject constructor(
    private val accountDetailUseCase: AccountDetailUseCase
) :
    BaseViewModel<TransferDestinationInputViewActions, TransferDestinationInputViewState, TransferDestinationInputViewEvents>(
        TransferDestinationInputViewState()
    ) {
    override fun handle(action: TransferDestinationInputViewActions) {
        when (action) {
            is TransferDestinationInputViewActions.CheckAccount -> handleSubmitAccount(action.accountValue)
            TransferDestinationInputViewActions.ClearAlert -> setState { it.copy(alertState = null) }
        }
    }

    private fun handleSubmitAccount(accountValue: String) {
        viewModelScope.launch {
            accountDetailUseCase.invoke(
                AccountDetailParam(
                    accountValue,
                    BankIdentifierNumberType.ACCOUNT
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { it.copy(loading = false) }
                                    },
                                    onDismissed = {
                                        setState { it.copy(loading = false) }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                accountDetail = result.data
                            )
                        }
                        postEvent(TransferDestinationInputViewEvents.NavigateToDestinationAmount(result.data))
                    }

                    is Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }
                }
            }
        }
    }
}