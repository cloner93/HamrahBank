package com.pmb.profile.presentaion.personal_infos.change_address.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.AddressEntity
import com.pmb.profile.domain.use_case.ChangeAddressUseCase
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeAddressViewModel @Inject constructor(
    private val changeAddressUseCase: ChangeAddressUseCase
) : BaseViewModel<ChangeAddressViewActions, ChangeAddressViewState, ChangeAddressViewEvents>(
    ChangeAddressViewState()
) {
    private val _shareState = MutableStateFlow<PersonalInfoSharedState>(PersonalInfoSharedState())
    val shareState: StateFlow<PersonalInfoSharedState> = _shareState

    override fun handle(action: ChangeAddressViewActions) {
        when (action) {
            ChangeAddressViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is ChangeAddressViewActions.UpdateShareState -> updateShareState(action.shareState)
            is ChangeAddressViewActions.ChangeAddress -> updatePostalCode(action.postalCode)
            ChangeAddressViewActions.SubmitAddress -> handleSubmitAddress()
            ChangeAddressViewActions.SubmitPostalCode -> inquiryPostalCode()
        }
    }

    private fun updatePostalCode(value: String) {
        if (value.length <= 10)
            setState {
                it.copy(
                    postalCode = value,
                    enablePostalCodeButton = value.length == 10 &&
                            !_shareState.value.addressEntity?.postalCode.equals(value)
                )
            }
    }

    private fun inquiryPostalCode() {
        viewModelScope.launch {
            changeAddressUseCase.invoke(
                ChangeAddressUseCase.Param(
                    id = 10L, postalCode = viewState.value.postalCode
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                enablePostalCodeButton = true,
                                alertState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertState = null) }
                                    }
                                )
                            )
                        }
                    }

                    Result.Loading -> {
                        setState {
                            it.copy(
                                loading = true,
                                enablePostalCodeButton = false
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                postalCode = result.data.postalCode.orEmpty(),
                                address = result.data.address.orEmpty(),
                                newInquiry = checkNewInquiry(result.data),
                                enablePostalCodeButton = false
                            )
                        }
                        _shareState.value = _shareState.value.copy(addressEntity = result.data)
                    }
                }
            }
        }
    }

    private fun checkNewInquiry(newAddressEntity: AddressEntity): Boolean =
        !(_shareState.value.addressEntity?.address.equals(newAddressEntity.address) &&
                _shareState.value.addressEntity?.postalCode.equals(newAddressEntity.postalCode))


    private fun updateShareState(shareState: PersonalInfoSharedState) {
        _shareState.value = shareState
        setState {
            it.copy(
                postalCode = shareState.addressEntity?.postalCode.orEmpty(),
                address = shareState.addressEntity?.address.orEmpty()
            )
        }
    }

    private fun handleSubmitAddress() {
        _shareState.value.addressEntity?.let {
            postEvent(ChangeAddressViewEvents.NavigateBackToPersonalInfo(it))
        }
    }
}