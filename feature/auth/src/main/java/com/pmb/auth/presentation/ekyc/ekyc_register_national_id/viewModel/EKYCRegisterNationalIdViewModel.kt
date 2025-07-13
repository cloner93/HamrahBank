package com.pmb.auth.presentation.ekyc.ekyc_register_national_id.viewModel
import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.national_id.entity.RegisterNationalIdRequest
import com.pmb.auth.domain.register.national_id.useCase.RegisterNationalIdUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EKYCRegisterNationalIdViewModel @Inject constructor(
    initialState: EKYCRegisterNationalIdViewState,
    private val repository: RegisterNationalIdUseCase
) : BaseViewModel<EKYCRegisterNationalIdViewActions, EKYCRegisterNationalIdViewState, EKYCRegisterNationalIdViewEvents>(
    initialState
) {
    override fun handle(action: EKYCRegisterNationalIdViewActions) {
        when (action) {
            is EKYCRegisterNationalIdViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is EKYCRegisterNationalIdViewActions.RegisterNationalIdSerialServices -> {
                handleRegisterNationalId(action)
            }
        }
    }

    private fun handleRegisterNationalId(action: EKYCRegisterNationalIdViewActions.RegisterNationalIdSerialServices) {
        viewModelScope.launch {
            repository.invoke(
                RegisterNationalIdRequest(action.nationalSerialId)
            ).collect { result ->
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
                                )

                            )
                        }
                    }

                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(isLoading = false)
                        }
                        postEvent(EKYCRegisterNationalIdViewEvents.RegisterNationalSucceed)
                    }
                }
            }
        }
    }

}
