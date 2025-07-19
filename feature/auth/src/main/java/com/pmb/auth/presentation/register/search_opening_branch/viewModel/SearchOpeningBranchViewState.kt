package com.pmb.auth.presentation.register.search_opening_branch.viewModel

import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranch
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranchEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class SearchOpeningBranchViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val openingBranch: OpeningBranchEntity? = null,
    val headTitle: String? = null,
    val selectedOpeningBranch: OpeningBranch? = null,
    val originalOpeningBranch: OpeningBranchEntity? = null
) : BaseViewState
