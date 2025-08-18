package com.pmb.account.presentation.issueCard.issueCardConfirm.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.account.presentation.issueCard.IssueCardSharedState
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.Result
import com.pmb.domain.model.card.RegisterCardRequest
import com.pmb.domain.usecae.card.RegisterCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class IssueCardConfirmViewModel @Inject constructor(
    initialState: IssueCardConfirmViewState,
    private val getRegisterCardUseCase: RegisterCardUseCase,
) : BaseViewModel<IssueCardConfirmViewActions, IssueCardConfirmViewState, IssueCardConfirmViewEvents>(
    initialState
) {
    override fun handle(action: IssueCardConfirmViewActions) {
        when (action) {
            IssueCardConfirmViewActions.NavigateBack -> {
                postEvent(IssueCardConfirmViewEvents.NavigateBack)
            }

            is IssueCardConfirmViewActions.SendData -> registerCard(action.data)
        }
    }

    private fun registerCard(value: IssueCardSharedState) {
        viewModelScope.launch {

            val params = RegisterCardRequest(
                cardGroup = value.cardGroup,
                accountType = value.cardOwnerAccount?.accountType ?: 0,
                issueRegionCode = value.userData?.output?.issueRegionCode ?: 0,
                requestType = value.userData?.output?.requestType ?: 0,
                incomeType = value.commissionFee?.incomeType ?: 0,
                birthDate = value.userData?.output?.birthDate ?: 0,
                lastPanExpireDate = 0,
                cityCode = (value.cityOfDeposit?.cityCode ?: 0).toLong(),
                formatId = value.formatId,
                accountNumber = value.accountNumber.toLong(),
                accountId = value.cardOwnerAccount?.accountId ?: 0,
                commissionAccountNumber = (value.commissionFeeAccount?.depositNumber
                    ?: "0").toLong(),
                nationalCode = value.userData?.output?.nationalCode ?: 0,
                pan = (value.selectedOldPan ?: "0").toLong(),
                gender = value.userData?.output?.gender,
                name = value.userData?.output?.name,
                fatherName = value.userData?.output?.fatherName,
                postalCode = (value.postalCode ?: value.userData?.output?.postalCode),
                idNumber = value.userData?.output?.idNumber,
                family = value.userData?.output?.family,
                address = value.address
            )

            getRegisterCardUseCase.invoke(params).collect { result ->
                when (result) {
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

                    Result.Loading -> setState { it.copy(isLoading = true) }
                    is Result.Success -> {
                        val res = result.data
                        setState {
                            it.copy(
                                isLoading = false
                            )
                        }


                        val json = Json { ignoreUnknownKeys = true }
                        val resJson = json.encodeToString(res)
                        val e = URLEncoder.encode(resJson, "UTF-8")

                        postEvent(IssueCardConfirmViewEvents.SuccessAnswer(e))
                    }
                }
            }
        }
    }
}

sealed interface IssueCardConfirmViewEvents : BaseViewEvent {
    object NavigateBack : IssueCardConfirmViewEvents
    class SuccessAnswer(val json: String) : IssueCardConfirmViewEvents
}

sealed interface IssueCardConfirmViewActions : BaseViewAction {
    object NavigateBack : IssueCardConfirmViewActions
    class SendData(val data: IssueCardSharedState) : IssueCardConfirmViewActions
}

data class IssueCardConfirmViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
) : BaseViewState