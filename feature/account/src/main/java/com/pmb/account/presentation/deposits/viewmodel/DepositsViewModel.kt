package com.pmb.account.presentation.deposits.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewEvents.NavigateToTransactionDetails
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewEvents.ShowToast
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.usecae.deposit.GetDefaultDepositUseCase
import com.pmb.domain.usecae.deposit.GetUserDepositListUseCase
import com.pmb.domain.usecae.deposit.SetDefaultDepositUseCase
import com.pmb.domain.usecae.transactions.TransactionsByCountPagingUsaCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import javax.inject.Inject


@HiltViewModel
open class DepositsViewModel @Inject constructor(
    initialState: DepositsViewState,
    private val getDepositsUseCase: GetUserDepositListUseCase,
    private val getTransactionsPaging: TransactionsByCountPagingUsaCase,
    private val getDefaultDepositUseCase: GetDefaultDepositUseCase,
    private val setDefaultDepositUseCase: SetDefaultDepositUseCase
) : BaseViewModel<DepositsViewActions, DepositsViewState, DepositsViewEvents>(initialState) {

    // we cont handle paging flow inside of ViewState.
    private val _transactionFlow =
        MutableStateFlow<PagingData<TransactionModel>>(PagingData.empty())
    val transactionFlow: StateFlow<PagingData<TransactionModel>> = _transactionFlow

    init {
        loadDeposits()
    }

    override fun handle(action: DepositsViewActions) {
        when (action) {
            is DepositsViewActions.ShowHelp -> {
                postEvent(ShowToast("Help information displayed"))
            }

            is DepositsViewActions.NavigateToBalanceScreen -> {
                postEvent(DepositsViewEvents.NavigateToBalanceScreen)
            }

            is DepositsViewActions.ShowShareBottomSheet -> {
                setState { it.copy(showShareDepositInfoBottomSheet = true) }
            }

            is DepositsViewActions.CloseShareBottomSheet -> {
                setState { it.copy(showShareDepositInfoBottomSheet = false) }
            }

            is DepositsViewActions.NavigateToTransactionScreen -> {
                postEvent(DepositsViewEvents.NavigateToTransactionsList)
            }

            is DepositsViewActions.NavigateToTransactionDetailScreen -> {

                // TODO: this is temporary.
                val json = Json { ignoreUnknownKeys = true }
                val transactionJson = json.encodeToString(action.transaction)
                val e = URLEncoder.encode(transactionJson, "UTF-8")

                postEvent(NavigateToTransactionDetails(e))
            }

            is DepositsViewActions.ShowDepositMoreActionBottomSheet -> {
                setState { it.copy(showMoreBottomSheet = true) }

            }

            is DepositsViewActions.SetAmountVisibility -> {
                setState { it.copy(isAmountVisible = !it.isAmountVisible) }
            }

            is DepositsViewActions.ShowDepositListBottomSheet -> {
                setState { it.copy(showDepositListBottomSheet = true) }
            }

            is DepositsViewActions.CloseDepositListBottomSheet -> {
                setState { it.copy(showDepositListBottomSheet = false) }

                if (action.model != null) {
                    selectDeposit(action.model)
                }
            }

            is DepositsViewActions.CloseDepositMoreActionBottomSheet -> {
                setState { it.copy(showMoreBottomSheet = false) }
            }

            DepositsViewActions.OpenDepositDetailsScreen -> {
                getDefaultDeposit()

            }

            is DepositsViewActions.SetDepositAsMain -> {
                action.model?.let {
                    viewModelScope.launch {
                        setDefaultDepositUseCase.invoke(action.model).collect{}
                    }
                }
            }

            DepositsViewActions.CloseGuideBottomSheet ->  {
                setState { it.copy(showGuideBottomSheet = false) }
            }
            DepositsViewActions.ShowGuideBottomSheet ->  {
                setState { it.copy(showGuideBottomSheet = true) }
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
                                    errorMessage = result.message, isLoading = false
                                )
                            }
                            postEvent(DepositsViewEvents.ShowError(result.message))
                        }

                        Result.Loading -> {
                            setState { it.copy(isLoading = true) }
                        }

                        is Result.Success -> {
                            val listOfDeposits: List<DepositModel> =
                                result.data.mapIndexed { index, deposit ->
                                    deposit.copy(isSelected = index == 0)
                                }
                            val selectedDeposit = listOfDeposits.firstOrNull()
                            val balance = listOfDeposits.sumOf { deposit -> deposit.amount }

                            setState {
                                it.copy(
                                    deposits = listOfDeposits,
                                    selectedDeposit = selectedDeposit,
                                    totalBalance = balance,
                                    isLoading = false
                                )
                            }

                            viewState.value.selectedDeposit?.let {
                                loadTransactions(
                                    it
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun loadTransactions(deposit: DepositModel) {

        getTransactionsPaging(
            TransactionRequest(
                extAccNo = deposit.depositNumber.toLong(),
                count = 10,
                categoryCode = deposit.categoryCode
            )
        ).cachedIn(viewModelScope).onEach { pagingData ->
                _transactionFlow.value = pagingData
            }.launchIn(viewModelScope)

    }

    /* private fun loadTransactions(deposit: DepositModel) {
         viewModelScope.launch {
             getTransactionsUseCase.invoke(
                 TransactionRequest(
                     extAccNo = deposit.depositNumber.toLong(),
                     count = 10,
                     categoryCode = deposit.categoryCode
                 )
             ).collect { result ->
                 when (result) {
                     is Result.Error -> {
                         setState {
                             it.copy(
                                 errorMessage = result.message,
                                 isLoading = false,
                                 transactions = listOf()
                             )
                         }
                         postEvent(DepositsViewEvents.ShowError(result.message))

                     }

                     Result.Loading -> {
                         setState { it.copy(isLoading = true) }
                     }

                     is Result.Success<*> -> {
                         val listOfTransactions: List<TransactionModel> =
                             result.data as List<TransactionModel>

                         setState {
                             it.copy(
                                 transactions = listOfTransactions,
                                 isLoading = false
                             )
                         }
                     }
                 }
             }
         }
     }*/

    private fun getDefaultDeposit() {
        viewModelScope.launch {
            getDefaultDepositUseCase.invoke(Unit).collect { result ->
                    when (result) {
                        is Result.Success -> {
                            val defaultDeposit = result.data
                            defaultDeposit?.let {
                                setState {
                                    it.copy(
                                        defaultDepositAccount = defaultDeposit
                                    )
                                }
                                viewState.value.selectedDeposit?.let { depositModel ->

                                    val json = Json { ignoreUnknownKeys = true }
                                    val deposit = json.encodeToString(depositModel)
                                    val e = URLEncoder.encode(deposit, "UTF-8")


                                    postEvent(DepositsViewEvents.NavigateToDetailsScreen(e))
                                }
                            }
                        }
                        is Result.Error ->{
                            viewState.value.selectedDeposit?.let { depositModel ->

                                val json = Json { ignoreUnknownKeys = true }
                                val deposit = json.encodeToString(depositModel)
                                val e = URLEncoder.encode(deposit, "UTF-8")


                                postEvent(DepositsViewEvents.NavigateToDetailsScreen(e))
                            }
                            setState { it.copy(showDepositListBottomSheet = true) }
                        }
                        else -> Unit
                    }
                }
        }
    }

    private fun selectDeposit(deposit: DepositModel) {
        val selectedDeposit =
            viewState.value.deposits.find { it.depositNumber == deposit.depositNumber }

        setState {
            it.copy(
                deposits = viewState.value.deposits.map { depositModel ->
                    if (depositModel.depositNumber == deposit.depositNumber) {
                            depositModel.copy(isSelected = true)
                        } else {
                            depositModel.copy(isSelected = false)
                        }
                    }

            )
        }

        setState { it.copy(selectedDeposit = selectedDeposit) }
        postEvent(DepositsViewEvents.DepositSelectionChanged(deposit.depositNumber))
        loadTransactions(deposit)
    }

    /*  fun hideAllBottomSheets() {
          setState {
              it.copy(
                  showShareDepositInfoBottomSheet = false,
                  showMoreBottomSheet = false,
                  showDepositListBottomSheet = false
              )
          }
      }*/

    /* fun refresh() {
         viewModelScope.launch {
             setState { it.copy(isRefreshing = true) }
             loadDeposits()
             setState { it.copy(isRefreshing = false) }
             postEvent(DepositsViewEvents.RefreshCompleted)
         }
     }*/
}