package com.pmb.auth.presentation.ekyc.ekyc_register_national_id.viewModel

import com.pmb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EKYCRegisterNationalIdViewModel @Inject constructor(
) : BaseViewModel<EKYCRegisterNationalIdViewActions, EKYCRegisterNationalIdViewState, EKYCRegisterNationalIdViewEvents>(
    EKYCRegisterNationalIdViewState()
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

            is EKYCRegisterNationalIdViewActions.SetNationalIdSerial -> {
                setState {
                    it.copy(
                        nationalSerialId = action.nationalIdSerial
                    )
                }
            }

        }
    }


}
