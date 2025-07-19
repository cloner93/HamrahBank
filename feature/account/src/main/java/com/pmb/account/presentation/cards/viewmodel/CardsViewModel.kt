package com.pmb.account.presentation.cards.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.Result
import com.pmb.domain.model.CardModel
import com.pmb.domain.usecae.card.GetUserCardListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    initialState: CardsViewState,
    private val getUserCardLIstUseCase: GetUserCardListUseCase,
) : BaseViewModel<CardsViewActions, CardsViewState, CardsViewEvents>(
    initialState
) {
    init {
        loadCards()
    }

    override fun handle(action: CardsViewActions) {
        when (action) {
            CardsViewActions.NavigateToBalanceScreen -> {
                postEvent(CardsViewEvents.NavigateToBalance)
            }

            is CardsViewActions.ShowDetailCardBottomSheetBottomSheet -> {
                setState {
                    it.copy(
                        showDetailCardBottomSheet = !it.showDetailCardBottomSheet,
                        selectedCard = action.model
                    )
                }
            }

            CardsViewActions.ShowFabBottomSheet -> {
                setState { it.copy(showFabBottomSheet = !it.showFabBottomSheet) }
            }

            CardsViewActions.ShowHelp -> {
                postEvent(CardsViewEvents.ShowHelp)
            }
        }
    }

    private fun loadCards() {
        viewModelScope.launch {
            getUserCardLIstUseCase.invoke(Unit)
                .collect { result ->
                    when (result) {
                        is Result.Error -> {
                            setState {
                                it.copy(
                                    errorMessage = result.message,
                                    isLoading = false
                                )
                            }
                            postEvent(CardsViewEvents.ShowError(result.message))
                        }

                        Result.Loading -> {
                            setState { it.copy(isLoading = true) }
                        }

                        is Result.Success<*> -> {
                            setState {
                                it.copy(
                                    cards = result.data as List<CardModel>,
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
        }
    }
}

sealed interface CardsViewEvents : BaseViewEvent {
    object NavigateToBalance : CardsViewEvents
    object ShowHelp : CardsViewEvents

    class ShowError(val error: String) : CardsViewEvents
}

sealed interface CardsViewActions : BaseViewAction {
    object NavigateToBalanceScreen : CardsViewActions
    object ShowHelp : CardsViewActions

    class ShowDetailCardBottomSheetBottomSheet(val model: CardModel?) : CardsViewActions
    object ShowFabBottomSheet : CardsViewActions
}

data class CardsViewState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val cards: List<CardModel> = emptyList<CardModel>(),
    val showDetailCardBottomSheet: Boolean = false,
    val showFabBottomSheet: Boolean = false,
    val selectedCard: CardModel? = null,
) : BaseViewState