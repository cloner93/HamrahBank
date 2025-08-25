package com.pmb.auth.presentation.activation.activation_tax_details.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface ActivationTaxDetailsViewEvents :BaseViewEvent{
    data object ConfirmTaxDetails:ActivationTaxDetailsViewEvents
}