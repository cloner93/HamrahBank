package com.pmb.auth.presentaion.ekyc.fee_details.viewModel

import com.pmb.auth.domain.ekyc.fee_details.entity.FeeDetailsEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class FeeDetailsViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val data :FeeDetailsEntity ?=null
) : BaseViewState