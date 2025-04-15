package com.pmb.transfer.presentation.transfer_confirm.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.PaymentType
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.use_case.SourceAccountBankUseCase
import com.pmb.transfer.domain.use_case.SourceCardBankUseCase
import com.pmb.transfer.domain.use_case.TransferConfirmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferConfirmViewModel @Inject constructor(
    private val sourceCardBankUseCase: SourceCardBankUseCase,
    private val sourceAccountBankUseCase: SourceAccountBankUseCase,
    private val transferConfirmUseCase: TransferConfirmUseCase
) :
    BaseViewModel<TransferConfirmViewActions, TransferConfirmViewState, TransferConfirmViewEvents>(
        TransferConfirmViewState()
    ) {
    private val _destinationAccount = MutableStateFlow<TransactionClientBankEntity?>(null)
    private val _destinationAmount = MutableStateFlow(0.0)
    private val _transferMethod = MutableStateFlow<TransferMethodEntity?>(null)


    override fun handle(action: TransferConfirmViewActions) {
        when (action) {
            TransferConfirmViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            TransferConfirmViewActions.SubmitTransferData -> handleSubmitTransferData()
            is TransferConfirmViewActions.SelectCardBank -> setState { it.copy(defaultCardBank = action.item) }
            is TransferConfirmViewActions.UpdateData -> handleUpdateData(action)
            is TransferConfirmViewActions.SelectAccountBank -> setState { it.copy(defaultAccountBank = action.item) }
            is TransferConfirmViewActions.UpdateFavoriteDestination -> setState {
                it.copy(
                    favoriteDestination = action.favorite
                )
            }

            is TransferConfirmViewActions.UpdateDepositId -> setState { it.copy(depositId = action.depositId) }
        }
    }

    private fun fetchData() {
        when (_transferMethod.value?.paymentType) {
            PaymentType.MELLAT_TO_MELLAT,
            PaymentType.CARD_TO_CARD -> fetchSourceCardBanks()

            PaymentType.INTERNAL_SATNA,
            PaymentType.INTERNAL_PAYA,
            PaymentType.INTERNAL_BRIDGE -> fetchSourceAccountBanks()

            null -> Unit
        }
    }

    private fun handleUpdateData(action: TransferConfirmViewActions.UpdateData) {
        _destinationAccount.value = action.account
        _destinationAmount.value = action.amount?.toDouble() ?: 0.0
        _transferMethod.value = action.transferMethod
        setState { it.copy(defaultReason = action.reason) }
        fetchData()
    }

    private fun fetchSourceCardBanks() {
        if (viewState.value.sourceCardBanks.isNotEmpty()) return
        viewModelScope.launch {
            sourceCardBankUseCase.invoke(SourceCardBankUseCase.Params(userId = "1"))
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

                        Result.Loading -> {
                            setState { it.copy(loading = true) }
                        }

                        is Result.Success -> {
                            setState {
                                it.copy(
                                    loading = false,
                                    defaultCardBank = result.data.firstOrNull { card -> card.defaulted }
                                        ?: result.data.firstOrNull(),
                                    sourceCardBanks = result.data
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun fetchSourceAccountBanks() {
        if (viewState.value.sourceAccountBanks.isNotEmpty()) return
        viewModelScope.launch {
            sourceAccountBankUseCase.invoke(SourceAccountBankUseCase.Params(userId = "1"))
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

                        Result.Loading -> {
                            setState { it.copy(loading = true) }
                        }

                        is Result.Success -> {
                            setState {
                                it.copy(
                                    loading = false,
                                    defaultAccountBank = result.data.firstOrNull { account -> account.defaulted }
                                        ?: result.data.firstOrNull(),
                                    sourceAccountBanks = result.data
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun handleSubmitTransferData() {
        viewModelScope.launch {
            transferConfirmUseCase.invoke(TransferConfirmUseCase.Params(value = "value"))
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

                        Result.Loading -> {
                            setState { it.copy(loading = true) }
                        }

                        is Result.Success -> {
                            setState { it.copy(loading = false) }
                            postEvent(
                                TransferConfirmViewEvents.NavigateToOtp(
                                    sourceCardBank = viewState.value.defaultCardBank,
                                    sourceAccountBank = viewState.value.defaultAccountBank,
                                    transferConfirm = result.data
                                )
                            )
                        }
                    }
                }
        }
    }
}