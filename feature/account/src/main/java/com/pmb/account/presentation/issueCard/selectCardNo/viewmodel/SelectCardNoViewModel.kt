package com.pmb.account.presentation.issueCard.selectCardNo.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.account.presentation.issueCard.selectCardNo.CardType
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.card.FetchCommissionForCreateCardResponse
import com.pmb.domain.model.openAccount.CardFormatModel
import com.pmb.domain.usecae.card.FetchCommissionForCreateParams
import com.pmb.domain.usecae.card.FetchCommissionForCreateUseCase
import com.pmb.domain.usecae.deposit.GetUserDepositListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCardNoViewModel @Inject constructor(
    initialState: SelectCardNoViewState,
    private val fetchCommissionForCreateUseCase: FetchCommissionForCreateUseCase,
    private val getDepositsUseCase: GetUserDepositListUseCase, // TODO: load deposit from memory
) : BaseViewModel<SelectCardNoViewActions, SelectCardNoViewState, SelectCardNoViewEvents>(
    initialState
) {
    init {
        fetchCommissionForCreate()
        loadDeposits()
    }

    override fun handle(action: SelectCardNoViewActions) {
        when (action) {
            SelectCardNoViewActions.NavigateBack -> {
                postEvent(SelectCardNoViewEvents.NavigateBack)
            }

            SelectCardNoViewActions.ClearAlertModelState -> {}
            is SelectCardNoViewActions.SelectCardFormat -> setState { it.copy(selectedCard = action.card) }
            is SelectCardNoViewActions.ChangeCardType -> setState { it.copy(cardType = action.cardType) }
            is SelectCardNoViewActions.SelectOldCard -> setState { it.copy(selectedOldCard = action.cardNo) }
            is SelectCardNoViewActions.SelectFeeDeposit -> setState { it.copy(selectedFeeDeposit = action.deposit) }
        }
    }

    private fun fetchCommissionForCreate() {
        viewModelScope.launch {
            val param = FetchCommissionForCreateParams(
                cardGroup = 1,
                accountNumber = 2095113315,
                isReplicate = 1,
                expireDate = 1
            )
            fetchCommissionForCreateUseCase.invoke(param).collect { result ->
                when (result) {
                    is Result.Loading -> setState { it.copy(isLoading = true) }
                    is Result.Error -> setState {
                        it.copy(
                            isLoading = false,
                            alertModelState = AlertModelState.Dialog(
                                title = "خطا",
                                description = " ${result.message}",
                                positiveButtonTitle = "تایید",
                                onPositiveClick = {
                                    setState { state -> state.copy(alertModelState = null) }
                                }
                            ))
                    }

                    is Result.Success -> {
                        val res = result.data

                        setState {
                            it.copy(
                                commissionFee = res
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadDeposits() {
        viewModelScope.launch {
            getDepositsUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                depositsError = "خطا در بارگیری اطلاعات"
                            )
                        }
                    }

                    Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true,
                                depositsError = null
                            )
                        }
                    }

                    is Result.Success -> {
                        val listOfDeposits: List<DepositModel> =
                            result.data.mapIndexed { index, deposit ->
                                deposit.copy(isSelected = index == 0)
                            }

                        setState {
                            it.copy(
                                isLoading = false,
                                depositsError = null,
                                deposits = listOfDeposits,
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed interface SelectCardNoViewEvents : BaseViewEvent {
    object NavigateBack : SelectCardNoViewEvents
}

sealed interface SelectCardNoViewActions : BaseViewAction {
    object NavigateBack : SelectCardNoViewActions
    object ClearAlertModelState : SelectCardNoViewActions
    data class SelectCardFormat(val card: CardFormatModel) : SelectCardNoViewActions
    data class ChangeCardType(val cardType: CardType) : SelectCardNoViewActions
    data class SelectOldCard(val cardNo: String) : SelectCardNoViewActions
    data class SelectFeeDeposit(val deposit: DepositModel) : SelectCardNoViewActions
}

data class SelectCardNoViewState(
    val isLoading: Boolean = false,
    val depositsError: String? = null,
    val selectedCard: CardFormatModel? = null,
    val spinnerText: String? = null,
    val cardType: CardType = CardType.UNSPECIFIED,
    val selectedOldCard: String? = null,
    val commissionFee: FetchCommissionForCreateCardResponse? = null,
    val deposits: List<DepositModel> = emptyList(),
    val selectedFeeDeposit: DepositModel? = null,
    val alertModelState: AlertModelState? = null
) : BaseViewState