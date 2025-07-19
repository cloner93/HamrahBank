package com.pmb.auth.presentation.scan_card_info.card_confirm.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.scan_card_info.card_confirmation.useCase.GetCardInformationConfirmationUseCase
import com.pmb.auth.domain.scan_card_info.card_confirmation.useCase.SendCardInformationConfirmationUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardInformationConfirmViewModel @Inject constructor(
    initialState: CardInformationConfirmViewState,
    private val sendCardInformationConfirmationUseCase: SendCardInformationConfirmationUseCase,
    private val getCardInformationConfirmationUseCase: GetCardInformationConfirmationUseCase
) : BaseViewModel<CardInformationConfirmViewActions, CardInformationConfirmViewState, CardInformationConfirmViewEvents>(
    initialState
) {
    override fun handle(action: CardInformationConfirmViewActions) {
        when (action) {
            is CardInformationConfirmViewActions.SendCardInformationConfirmation -> {
                handleSendConfirmation()
            }

            is CardInformationConfirmViewActions.GetCardInformation -> {
                handleGetCardInformation()
            }

            is CardInformationConfirmViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun handleSendConfirmation() {

        viewModelScope.launch {
            sendCardInformationConfirmationUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(isLoading = true)
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                            )
                        }
                        postEvent(CardInformationConfirmViewEvents.SendCardInformationConfirmation)
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false, alertModelState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleGetCardInformation() {
        viewModelScope.launch {
            getCardInformationConfirmationUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(isLoading = true)
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                data = result.data
                            )
                        }
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false, alertModelState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                }
            }
        }
    }
    init {
        handle(CardInformationConfirmViewActions.GetCardInformation)
    }
}