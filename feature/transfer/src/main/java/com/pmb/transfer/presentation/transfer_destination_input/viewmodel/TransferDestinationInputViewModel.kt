package com.pmb.transfer.presentation.transfer_destination_input.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.param.AccountDetailParam
import com.pmb.transfer.domain.use_case.AccountDetailUseCase
import com.pmb.transfer.domain.use_case.SourceAccountBankUseCase
import com.pmb.transfer.utils.BankUtil.extractOrderByIdentifier
import com.pmb.transfer.utils.BankUtil.formatGropedWithSeparator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.invoke

@HiltViewModel
class TransferDestinationInputViewModel @Inject constructor(
    private val sourceAccountBankUseCase: SourceAccountBankUseCase,
    private val accountDetailUseCase: AccountDetailUseCase
) :
    BaseViewModel<TransferDestinationInputViewActions, TransferDestinationInputViewState, TransferDestinationInputViewEvents>(
        TransferDestinationInputViewState()
    ) {
    init {
        fetchSourceAccount() // this must call before call other api for transfer flow
    }

    override fun handle(action: TransferDestinationInputViewActions) {
        when (action) {
            TransferDestinationInputViewActions.CheckAccount -> handleSubmitAccount(viewState.value.identifierNumber)
            TransferDestinationInputViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferDestinationInputViewActions.UpdateIdentifierNumber ->
                handleUpdateIdentifierNumber(action.value)

            is TransferDestinationInputViewActions.ReadTextFromClipboard ->
                handleReadTextFromClipboard(action.value)

            TransferDestinationInputViewActions.ClickedOnClipboard -> handleClickedOnClipboard(
                viewState.value.identifierNumberClipboard
            )
        }
    }

    private fun handleClickedOnClipboard(value: String) {
        value.extractOrderByIdentifier { type, id ->
            fetchAccountDetail(type = type, id = id, success = { data ->
                setState { it.copy(loading = false, accountDetail = data) }
            })
        }
    }

    private fun fetchAccountDetail(
        type: BankIdentifierNumberType,
        id: String,
        success: (TransactionClientBankEntity) -> Unit
    ) {
        viewModelScope.launch {
            accountDetailUseCase.invoke(
                AccountDetailParam(id, type)
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
                        success.invoke(result.data)
                    }

                    is Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }
                }
            }
        }
    }

    private fun handleReadTextFromClipboard(value: String) {
        value.extractOrderByIdentifier { type, id ->
            when (type) {
                BankIdentifierNumberType.CARD,
                BankIdentifierNumberType.IBAN ->
                    setState {
                        it.copy(
                            identifierNumberClipboard = id.formatGropedWithSeparator(),
                            clipboardTextType = type
                        )
                    }

                BankIdentifierNumberType.ACCOUNT ->
                    setState {
                        it.copy(
                            identifierNumberClipboard = id,
                            clipboardTextType = type
                        )
                    }
            }
        }
    }

    private fun handleUpdateIdentifierNumber(value: String) {
        setState { it.copy(identifierNumber = value) }
    }

    private fun handleSubmitAccount(accountValue: String) {
        accountValue.extractOrderByIdentifier { type, id ->
            fetchAccountDetail(type = type, id = id, success = { data ->
                setState { it.copy(loading = false) }
                postEvent(TransferDestinationInputViewEvents.NavigateToDestinationAmount(data))
            })
        }
    }

    private fun fetchSourceAccount() {
        viewModelScope.launch {
            sourceAccountBankUseCase.invoke(SourceAccountBankUseCase.Params(userId = "1"))
                .collect { result ->
                    when (result) {
                        is Result.Error ->
                            setState {
                                it.copy(
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

                        Result.Loading -> Unit
                        is Result.Success -> Unit
                    }
                }
        }
    }
}