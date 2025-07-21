package com.pmb.auth.presentation.register.authentication_information.viewModel

import com.pmb.auth.domain.Education
import com.pmb.calender.longToString
import com.pmb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationInformationViewModel @Inject constructor(
    initialState: AuthenticationInformationViewState,
) : BaseViewModel<AuthenticationInformationViewActions, AuthenticationInformationViewState, AuthenticationInformationViewEvents>(
    initialState
) {
    private val educationList = listOf(
        Education(1, "دیپلم"),
        Education(2, "فوق دیپلم"),
        Education(3, "لیسانس"),
        Education(4, "فوق لیسانس"),
        Education(5, "دکترا"),
        Education(6, "حوزوی"),
        Education(7, "سیکل"), Education(8, "ابتدایی"), Education(9, "بیسواد")
    )

    fun getEducationList() = educationList

    override fun handle(action: AuthenticationInformationViewActions) {
        when (action) {
            AuthenticationInformationViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is AuthenticationInformationViewActions.SetAuthenticationData -> {
                handleAuthenticationData(action)
            }

            is AuthenticationInformationViewActions.SetCityId -> {
                handleSetCityId(action)
            }

            is AuthenticationInformationViewActions.SetIdentifyPlaceId -> {
                handleIdentifyPlaceId(action)
            }

            is AuthenticationInformationViewActions.SetEducation -> {
                handleSetEducation(action)
            }

            is AuthenticationInformationViewActions.SetIssueDate -> {
                handleSetIdentifyDay(action)
            }

            is AuthenticationInformationViewActions.SetPhoneNumber -> {
                handleSetPhoneNumber(action)
            }

            is AuthenticationInformationViewActions.SetIssueRegion -> {
                handleSetIdentifyArea(action)
            }
        }
    }

    private fun handleAuthenticationData(action: AuthenticationInformationViewActions.SetAuthenticationData) {
        val issueDate = action.sharedViewState.verifyCodeResponse?.issueDate?.toLong()?.longToString()
        setState {
            it.copy(
                birthDatePlace = it.birthDatePlace ?: action.sharedViewState.birthDatePlace,
                issuePlace = it.issuePlace ?: action.sharedViewState.issuePlace,
                issueCode = it.issueCode ?: action.sharedViewState.issueRgnCode,
                tel = it.tel ?: action.sharedViewState.tel,
                issueDateYear = it.issueDateYear ?: issueDate?.first,
                issueDateMonth = it.issueDateMonth ?: issueDate?.second,
                issueDateDay = it.issueDateDay ?: issueDate?.third,
            )
        }
    }

    private fun handleSetCityId(action: AuthenticationInformationViewActions.SetCityId) {
        setState {
            it.copy(
                birthDatePlace = action.city
            )
        }
    }

    private fun handleSetIdentifyDay(action: AuthenticationInformationViewActions.SetIssueDate) {
        setState {
            it.copy(
                issueDateYear = action.issueDateYear,
                issueDateMonth = action.issueDateMonth,
                issueDateDay = action.issueDateDay,
            )
        }
    }

    private fun handleSetIdentifyArea(action: AuthenticationInformationViewActions.SetIssueRegion) {
        setState {
            it.copy(
                issueCode = action.identifyArea
            )
        }
    }

    private fun handleSetPhoneNumber(action: AuthenticationInformationViewActions.SetPhoneNumber) {
        setState {
            it.copy(
                tel = action.phoneNumber
            )
        }
    }

    private fun handleIdentifyPlaceId(action: AuthenticationInformationViewActions.SetIdentifyPlaceId) {
        setState {
            it.copy(
                issuePlace = action.city
            )
        }
    }

    private fun handleSetEducation(action: AuthenticationInformationViewActions.SetEducation) {
        setState {
            it.copy(
                education = action.eduction
            )

        }
    }

}