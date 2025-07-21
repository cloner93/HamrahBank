package com.pmb.account.presentation.account.details

import androidx.lifecycle.SavedStateHandle
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.DepositModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class DepositDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: DepositDetailsViewState,
) : BaseViewModel<DepositDetailsViewActions, DepositDetailsViewState, DepositDetailsViewEvents>(
    initialState
) {

    private val deposit = savedStateHandle.get<String>("deposit")

    init {

        val json = Json { ignoreUnknownKeys = true }
        val jsonString = URLDecoder.decode(deposit, "UTF-8")
        val deposit = json.decodeFromString<DepositModel>(jsonString)

        setState {
            it.copy(
                depositDetail = DepositDetail(
                    deposit.branchName,
                    deposit.lastTransactionDate.toString(),
                    deposit.ibanNumber,
                    deposit.title,
                    deposit.profitValue.toString()
                )
            )
        }
    }

    override fun handle(action: DepositDetailsViewActions) {
    }
}

sealed interface DepositDetailsViewEvents : BaseViewEvent

sealed interface DepositDetailsViewActions : BaseViewAction

data class DepositDetailsViewState(
    val depositDetail: DepositDetail? = null
) : BaseViewState

data class DepositDetail(
    val branchName: String?,
    val lastTransactionDate: String?,
    val ibanCode: String?,
    val accountType: String?,
    val interestRate: String?
)