package com.pmb.auth.presentaion.ekyc.feeDetails.viewModel

import com.pmb.auth.domain.ekyc.feeDetails.entity.FeeDetailsEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class FeeDetailsViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val data :FeeDetailsEntity ?=null
) : BaseViewState