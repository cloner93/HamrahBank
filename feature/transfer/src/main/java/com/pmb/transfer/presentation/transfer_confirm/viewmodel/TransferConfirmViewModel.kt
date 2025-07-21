package com.pmb.transfer.presentation.transfer_confirm.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.data.serviceProvider.local.LocalServiceProvider
import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.entity.PaymentType
import com.pmb.transfer.domain.entity.TransferConfirmEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.domain.entity.TransferSourceEntity
import com.pmb.transfer.domain.use_case.SourceAccountBankUseCase
import com.pmb.transfer.domain.use_case.SourceCardBankUseCase
import com.pmb.transfer.domain.use_case.TransferConfirmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferConfirmViewModel @Inject constructor(
    private val sourceCardBankUseCase: SourceCardBankUseCase,
    private val sourceAccountBankUseCase: SourceAccountBankUseCase,
    private val transferConfirmCardUseCase: TransferConfirmUseCase,
    private val localServiceProvider: LocalServiceProvider
) : BaseViewModel<TransferConfirmViewActions, TransferConfirmViewState, TransferConfirmViewEvents>(
    TransferConfirmViewState()
) {


    override fun handle(action: TransferConfirmViewActions) {
        when (action) {
            TransferConfirmViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            TransferConfirmViewActions.SubmitTransferData -> handleSubmitTransferData()
            is TransferConfirmViewActions.SelectCardBank -> setState {
                it.copy(defaultSource = TransferSourceEntity.Card(action.item))
            }

            is TransferConfirmViewActions.UpdateData -> handleUpdateData(action)
            is TransferConfirmViewActions.SelectAccountBank -> setState {
                it.copy(defaultSource = TransferSourceEntity.Account(action.item))
            }

            is TransferConfirmViewActions.UpdateFavoriteDestination -> setState {
                it.copy(
                    favoriteDestination = action.favorite
                )
            }

            is TransferConfirmViewActions.UpdateDepositId -> setState { it.copy(depositId = action.depositId) }
        }
    }

    private fun fetchData() {
        when (viewState.value.transferMethod?.paymentType) {
            PaymentType.CARD_TO_CARD -> fetchSourceCardBanks()

            PaymentType.MELLAT_TO_MELLAT, PaymentType.INTERNAL_SATNA, PaymentType.INTERNAL_PAYA, PaymentType.INTERNAL_BRIDGE -> fetchSourceAccountBanks()

            null -> Unit
        }
    }

    private fun handleUpdateData(action: TransferConfirmViewActions.UpdateData) {
        setState {
            it.copy(
                destinationAccount = action.account,
                destinationAmount = action.amount ?: 0.0,
                transferMethod = action.transferMethod,
                defaultReason = action.reason
            )
        }
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

                        Result.Loading -> {
                            setState { it.copy(loading = true) }
                        }

                        is Result.Success -> {
                            val defaultSource = (result.data.firstOrNull { card -> card.defaulted }
                                ?: result.data.firstOrNull())?.let { TransferSourceEntity.Card(card = it) }
                            setState {
                                it.copy(
                                    loading = false,
                                    defaultSource = defaultSource,
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
            sourceAccountBankUseCase.invoke(
                SourceAccountBankUseCase.Params(
                    userId = localServiceProvider.getUserDataStore().getUserData()?.customerId
                        ?: return@launch
                )
            )
                .collect { result ->
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

                        Result.Loading -> {
                            setState { it.copy(loading = true) }
                        }

                        is Result.Success -> {
                            val items = result.data.map {
                                it.copy(
                                    accountHolderName = localServiceProvider.getUserDataStore()
                                        .getUserData()?.fullName
                                )
                            }
                            val defaultSource =
                                (items.firstOrNull { account -> account.defaulted }
                                    ?: items.firstOrNull())?.let {
                                    TransferSourceEntity.Account(account = it)
                                }
                            setState {
                                it.copy(
                                    loading = false,
                                    defaultSource = defaultSource,
                                    sourceAccountBanks = items
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun handleSubmitTransferData() {
        val destinationNumber = when (viewState.value.destinationAccount?.type) {
            BankIdentifierNumberType.ACCOUNT -> viewState.value.destinationAccount?.clientBankEntity?.accountNumber
            BankIdentifierNumberType.CARD -> viewState.value.destinationAccount?.clientBankEntity?.cardNumber
            BankIdentifierNumberType.IBAN -> viewState.value.destinationAccount?.clientBankEntity?.iban
            null -> null
        }

        viewModelScope.launch {
            val params = viewState.value.run {
                val sourceNumber = when (defaultSource) {
                    is TransferSourceEntity.Card -> defaultSource.card.cardNumber
                    is TransferSourceEntity.Account -> defaultSource.account.accountNumber
                    null -> return@launch
                }

                TransferConfirmUseCase.Params(
                    sourceNumber = sourceNumber ?: return@launch,
                    destinationNumber = destinationNumber ?: return@launch,
                    customerId = localServiceProvider.getUserDataStore().getUserData()?.customerId ?:"",
                    amount = destinationAmount,
                    reasonId = defaultReason?.id,
                    depositId = depositId,
                    transferMethod = transferMethod?.paymentType ?: return@launch,
                    favoriteDestination = favoriteDestination
                )
            }

            transferConfirmCardUseCase.invoke(params).collect { result ->
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

                    Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }

                    is Result.Success -> {
                        setState { it.copy(loading = false) }

                        when (val model = result.data) {
                            is TransferConfirmEntity.ReceiptConfirm -> {
                                val receipt = gerReceipt(
                                    receipt = model.receipt.copy(
                                        source = viewState.value.defaultSource,
                                        destination = viewState.value.destinationAccount,
                                    )
                                )
                                postEvent(
                                    TransferConfirmViewEvents.NavigateToReceipt(
                                        receipt = receipt
                                    )
                                )
                            }

                            is TransferConfirmEntity.CardVerificationRequired ->
                                postEvent(
                                    TransferConfirmViewEvents.NavigateToCardVerification(
                                        source = viewState.value.defaultSource
                                            ?: throw IllegalStateException("No valid source"),
                                        verificationInfo = model.verificationInfo
                                    )
                                )
                        }
                    }
                }
            }
        }
    }


    private fun gerReceipt(receipt: TransferReceiptEntity): TransferReceiptEntity {
        val destination = viewState.value.destinationAccount
            ?: throw IllegalStateException("No valid destination account")
        val paymentType = viewState.value.transferMethod?.paymentType
            ?: throw IllegalStateException("No valid payment type")
        val source = viewState.value.defaultSource
            ?: throw IllegalStateException("No valid source")

        return receipt.copy(
            source = source,
            destination = destination,
            amount = viewState.value.destinationAmount,
            paymentType = paymentType,
            trackingNumber = receipt.trackingNumber,
            message = receipt.message
        )
    }
}