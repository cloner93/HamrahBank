package com.pmb.account.presentation.issueCard.selectAddress.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.account.presentation.issueCard.selectAddress.AddressType
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.Result
import com.pmb.domain.model.card.City
import com.pmb.domain.model.openAccount.accountType.Province
import com.pmb.domain.usecae.card.FetchCityListParams
import com.pmb.domain.usecae.card.FetchCityListUseCase
import com.pmb.domain.usecae.card.FetchPostCodeUseCase
import com.pmb.domain.usecae.card.FetchProvinceListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectAddressViewModel @Inject constructor(
    initialState: SelectAddressViewState,
    private val fetchPostCodeUseCase: FetchPostCodeUseCase,
    private val fetchProvinceList: FetchProvinceListUseCase,
    private val fetchCityListUseCase: FetchCityListUseCase,
) : BaseViewModel<SelectAddressViewActions, SelectAddressViewState, SelectAddressViewEvents>(
    initialState
) {
    init {
        fetchProvinceList()
    }

    override fun handle(action: SelectAddressViewActions) {
        when (action) {
            SelectAddressViewActions.NavigateBack -> {
                postEvent(SelectAddressViewEvents.NavigateBack)
            }

            SelectAddressViewActions.InquiryPostCode -> {
                fetchPostCodeInquiry()
            }

            is SelectAddressViewActions.ChangeAddressType -> setState {
                it.copy(
                    addressType = action.addressType,
                    address = action.address
                )
            }

            is SelectAddressViewActions.ChangePostalCode -> setState {
                it.copy(
                    postalCode = action.postalCode,
                    postalCodeError = null
                )
            }

            SelectAddressViewActions.FetchCardShema -> {
            }

            is SelectAddressViewActions.ChangeCity -> fetchCityList(action.province.provinceCode)
        }
    }

    private fun fetchProvinceList() {
        viewModelScope.launch {
            fetchProvinceList.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
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
                    }

                    Result.Loading -> {
                        setState { it.copy(isLoading = true) }
                    }

                    is Result.Success -> {
                        val provinceList = result.data
                        setState {
                            it.copy(
                                isLoading = false,
                            )
                        }

                        postEvent(SelectAddressViewEvents.UpdateProvinceList(provinceList))
                    }
                }
            }
        }
    }

    private fun fetchCityList(province: Int) {
        viewModelScope.launch {
            val param = FetchCityListParams(provinceCode = province)

            fetchCityListUseCase.invoke(param).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
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
                    }

                    Result.Loading -> {
                        setState { it.copy(isLoading = true) }
                    }

                    is Result.Success -> {
                        val cityList = result.data
                        setState {
                            it.copy(
                                isLoading = false,
                            )
                        }

                        postEvent(SelectAddressViewEvents.UpdateCityList(cityList))
                    }
                }
            }
        }
    }

    private fun fetchPostCodeInquiry() {
        viewModelScope.launch {
            val postCode = viewState.value.postalCode
            fetchPostCodeUseCase.invoke(postCode?.toLong() ?: 0).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                postalCodeError = result.message
                            )
                        }
                    }

                    Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true,
                                postalCodeError = null
                            )
                        }
                    }

                    is Result.Success -> {
                        val address = result.data.address
                        setState {
                            it.copy(
                                isLoading = false,
                                postalCodeError = null,
                                address = address
                            )
                        }
                    }
                }
            }
        }
    }

}

sealed interface SelectAddressViewEvents : BaseViewEvent {
    object NavigateBack : SelectAddressViewEvents
    class UpdateProvinceList(val provinceList: List<Province>) : SelectAddressViewEvents
    class UpdateCityList(val cityList: List<City>) : SelectAddressViewEvents
}

sealed interface SelectAddressViewActions : BaseViewAction {
    object NavigateBack : SelectAddressViewActions
    object InquiryPostCode : SelectAddressViewActions
    object FetchCardShema : SelectAddressViewActions
    class ChangeCity(val province: Province) : SelectAddressViewActions
    class ChangeAddressType(val addressType: AddressType, val address: String?) :
        SelectAddressViewActions

    class ChangePostalCode(val postalCode: String) : SelectAddressViewActions
}

data class SelectAddressViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val addressType: AddressType = AddressType.UNSPECIFIED,
    val address: String? = null,
    val postalCodeError: String? = null,
    val postalCode: String? = null,
) : BaseViewState