package com.pmb.transfer.presentation.transfer_destination_input.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.param.AccountDetailParam
import com.pmb.transfer.domain.use_case.AccountDetailUseCase
import com.pmb.transfer.utils.BankUtil
import com.pmb.transfer.utils.BankUtil.formatGropedWithSeparator
import com.pmb.transfer.utils.BankUtil.isValidIranBankIdentifier
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
            TransferDestinationInputViewActions.CheckAccount -> handleSubmitAccount(viewState.value.identifierNumber)
            TransferDestinationInputViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferDestinationInputViewActions.UpdateIdentifierNumber ->
                handleUpdateIdentifierNumber(action.value)

            is TransferDestinationInputViewActions.ReadTextFromClipboard ->
                handleReadTextFromClipboard(action.value)

            TransferDestinationInputViewActions.ClickedOnClipboard -> handleClickedOnClipboard()
        }
    }

    private fun handleClickedOnClipboard() {
        viewState.value.identifierNumberClipboard.trim().replace(" ", "")
            .takeIf { it.isValidIranBankIdentifier() }?.let { id ->
                val isCard = id.length == 16 && BankUtil.getBankByCardNumber(id) != null
                val isSheba = id.length == 26 && BankUtil.getBankBySheba(id) != null
                val isAccount = BankUtil.getBankByAccountNumber(id) != null
                val clipTextType = when {
                    isCard -> BankIdentifierNumberType.CARD
                    isSheba -> BankIdentifierNumberType.IBAN
                    else -> BankIdentifierNumberType.ACCOUNT

                }
                viewModelScope.launch {
                    accountDetailUseCase.invoke(
                        AccountDetailParam(id, clipTextType)
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
                            }

                            is Result.Loading -> {
                                setState { it.copy(loading = true) }
                            }
                        }
                    }
                }
            }
    }

    private fun handleReadTextFromClipboard(value: String) {
        value.trim().replace(" ", "")
            .takeIf { it.isValidIranBankIdentifier() }?.let { id ->
                val isCard = id.length == 16 && BankUtil.getBankByCardNumber(id) != null
                val isSheba = id.length == 26 && BankUtil.getBankBySheba(id) != null
                val isAccount = BankUtil.getBankByAccountNumber(id) != null
                val clipTextType = when {
                    isCard -> BankIdentifierNumberType.CARD
                    isSheba -> BankIdentifierNumberType.IBAN
                    isAccount -> BankIdentifierNumberType.ACCOUNT
                    else -> null
                }
                when {
                    isCard || isSheba ->
                        setState {
                            it.copy(
                                identifierNumberClipboard = id.formatGropedWithSeparator(),
                                clipboardTextType = clipTextType
                            )
                        }

                    isAccount -> setState {
                        it.copy(
                            identifierNumberClipboard = id,
                            clipboardTextType = clipTextType
                        )
                    }
                }
            }
    }

    private fun handleUpdateIdentifierNumber(value: String) {
        setState { it.copy(identifierNumber = value) }
    }

    private fun handleSubmitAccount(accountValue: String) {
        val isCard = accountValue.length == 16 && BankUtil.getBankByCardNumber(accountValue) != null
        val isSheba = accountValue.length == 26 && BankUtil.getBankBySheba(accountValue) != null
        val isAccount = BankUtil.getBankByAccountNumber(accountValue) != null
        val clipTextType = when {
            isCard -> BankIdentifierNumberType.CARD
            isSheba -> BankIdentifierNumberType.IBAN
            else -> BankIdentifierNumberType.ACCOUNT

        }
        viewModelScope.launch {
            accountDetailUseCase.invoke(AccountDetailParam(accountValue, clipTextType))
                .collect { result ->
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
                            postEvent(
                                TransferDestinationInputViewEvents.NavigateToDestinationAmount(
                                    result.data
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