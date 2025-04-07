package com.pmb.auth.presentation.scan_card_info.card_info.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.scan_card_info.card_info.entity.CardInfoParams
import com.pmb.auth.domain.scan_card_info.card_info.useCase.SendCardInfoUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardInfoViewModel @Inject constructor(
    initialState: CardInfoViewState,
    private val sendCardInfoUseCase: SendCardInfoUseCase
) : BaseViewModel<CardInfoViewActions, CardInfoViewState, CardInfoViewEvents>(initialState) {
    override fun handle(action: CardInfoViewActions) {
        when (action) {
            is CardInfoViewActions.ClearAlert -> {
                setState {
                    it.copy(isLoading = false)
                }
            }

            is CardInfoViewActions.SendCardInfo -> {
                handleSendCardInfo(action)
            }
        }
    }

    private fun handleSendCardInfo(action: CardInfoViewActions.SendCardInfo) {
        viewModelScope.launch {
            sendCardInfoUseCase.invoke(
                CardInfoParams(
                    cardNumber = action.cardNumber,
                    cvv2 = action.cvv2,
                    month = action.month,
                    year = action.month,
                    password = action.password
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false, alertModelState = AlertModelState.Dialog(
                                    title = "Login Failed",
                                    description = "failed to login because ${result.message}",
                                    positiveButtonTitle = "okay",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                    Result.Loading -> setState { it.copy(isLoading = true) }
                    is Result.Success -> {
                        setState { it.copy(isLoading = false) }
                        postEvent(CardInfoViewEvents.SendCardInfoSucceed)
                    }
                }
            }
        }
    }

}