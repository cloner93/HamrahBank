package com.pmb.auth.presentation.activation.activation_tax_details.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface ActivationTaxDetailsViewActions : BaseViewAction {
    data object ClearAlert : ActivationTaxDetailsViewActions
    data object LoadActivationTaxDetails : ActivationTaxDetailsViewActions
    data class SendActivationTaxDetailsData(val accountNumber: String) :
        ActivationTaxDetailsViewActions
}