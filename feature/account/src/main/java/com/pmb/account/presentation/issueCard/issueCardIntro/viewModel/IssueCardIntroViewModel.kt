package com.pmb.account.presentation.issueCard.issueCardIntro.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.Result
import com.pmb.domain.model.card.CardCustomerAddressResponse
import com.pmb.domain.usecae.card.CardCustomerAddressParams
import com.pmb.domain.usecae.card.GetCustomerAddressForCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueCardIntroViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: IssueCardIntroViewState,
    private val getCustomerAddressForCardUseCase: GetCustomerAddressForCardUseCase
) : BaseViewModel<IssueCardIntroViewActions, IssueCardIntroViewState, IssueCardIntroViewEvents>(
    initialState
) {
    private val accountNumber = savedStateHandle.get<String>("accountNumber") ?: "0"
    private val cardGroup = savedStateHandle.get<Long>("cardGroup") ?: 1

    init {
        setState {
            it.copy(
                accountNumber = accountNumber,
                cardGroup = cardGroup
            )
        }
    }

    override fun handle(action: IssueCardIntroViewActions) {
        when (action) {
            IssueCardIntroViewActions.NavigateBack -> {
                postEvent(IssueCardIntroViewEvents.NavigateBack)
            }

            IssueCardIntroViewActions.DoActionInApp -> {
                loadCustomerAddress()
            }
        }
    }

    private fun loadCustomerAddress() {
        viewModelScope.launch {
            val cardParams = CardCustomerAddressParams(
                accountNumber = accountNumber.toLong(),
                cardGroup = cardGroup
            )

            getCustomerAddressForCardUseCase(cardParams).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
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
                    }

                    Result.Loading -> {
                        setState { it.copy(isLoading = true) }
                    }

                    is Result.Success -> {
                        val res = result.data

                        setState {
                            it.copy(
                                isLoading = false,
                            )
                        }
                        postEvent(IssueCardIntroViewEvents.NavigateToChooseAddress(res))
                    }
                }
            }
        }
    }
}

sealed interface IssueCardIntroViewEvents : BaseViewEvent {
    object NavigateBack : IssueCardIntroViewEvents
    class NavigateToChooseAddress(val data: CardCustomerAddressResponse) : IssueCardIntroViewEvents
}

sealed interface IssueCardIntroViewActions : BaseViewAction {
    object NavigateBack : IssueCardIntroViewActions
    object DoActionInApp : IssueCardIntroViewActions
}

data class IssueCardIntroViewState(
    val isLoading: Boolean = false,
    val accountNumber: String = "",
    val cardGroup: Long = 1,
    val alertModelState: AlertModelState? = null
) : BaseViewState