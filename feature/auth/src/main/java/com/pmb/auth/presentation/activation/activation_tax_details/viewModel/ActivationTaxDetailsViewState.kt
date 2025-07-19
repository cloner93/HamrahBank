package com.pmb.auth.presentation.activation.activation_tax_details.viewModel

import com.pmb.auth.domain.ekyc.fee_details.entity.FeeDetailsEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class ActivationTaxDetailsViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val data : FeeDetailsEntity?=null
) : BaseViewState