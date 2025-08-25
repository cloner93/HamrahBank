package com.pmb.auth.presentation.register.account_opening.viewModel

import com.pmb.core.platform.BaseViewModel
import com.pmb.domain.usecae.auth.openAccount.GenerateCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OpeningAccountViewModel @Inject constructor(
    private val generateCodeUseCase: GenerateCodeUseCase
) :
    BaseViewModel<OpeningAccountViewActions, OpeningAccountViewState, OpeningAccountViewEvents>(
        OpeningAccountViewState()
    ) {
    override fun handle(action: OpeningAccountViewActions) {
        when (action) {
            is OpeningAccountViewActions.ClearAlertModelState -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is OpeningAccountViewActions.SetBirthday -> {
                handleSetBirthday(action)
            }

            is OpeningAccountViewActions.SetNationalId -> {
                handleSetNationalId(action.nationalId)
            }

            is OpeningAccountViewActions.SetPhoneNumber -> {
                handleSetPhoneNumber(action.phoneNumber)
            }

            is OpeningAccountViewActions.ShowBottomSheet -> {
                handleShowBottomSheet(action.isVisible)
            }

            is OpeningAccountViewActions.SendOpeningAccountData -> {
                handleSendOpeningAccountData()
            }
        }
    }

    private fun handleSendOpeningAccountData(

    ) {
        postEvent(OpeningAccountViewEvents.SendOpeningAccountViewSucceed)
    }

    private fun handleShowBottomSheet(isVisible: Boolean) {
        setState {
            it.copy(
                isShowingBottomSheet = isVisible
            )
        }
    }

    private fun handleSetPhoneNumber(phoneNumber: String) {
        setState {
            it.copy(
                phoneNumber = phoneNumber
            )
        }
    }

    private fun handleSetNationalId(nationalId: String) {
        setState {
            it.copy(
                nationalId = nationalId
            )
        }
    }

    private fun handleSetBirthday(actions: OpeningAccountViewActions.SetBirthday) {
        setState {
            it.copy(
                birthDateDay = actions.birthDateDay,
                birthDateYear = actions.birthDateYear,
                birthDateMonth = actions.birthDateMonth,
            )
        }
    }

}