package com.pmb.account.presentation.cards.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.account.presentation.component.CardModel
import com.pmb.account.usecase.deposits.GetCardsUseCase
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    initialState: CardsViewState,
    private val getDepositsUseCase: GetCardsUseCase,
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
            setState { it.copy(isLoading = true) }
            try {

                val listOfCards = getDepositsUseCase()

                setState {
                    it.copy(
                        cards = listOfCards,
                        isLoading = false
                    )
                }

            } catch (
                e: Exception
            ) {

                setState {
                    it.copy(
                        errorMessage = e.message ?: "Failed to load deposits",
                        isLoading = false
                    )
                }
                postEvent(CardsViewEvents.ShowError(e.message ?: "Failed to load deposits"))
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