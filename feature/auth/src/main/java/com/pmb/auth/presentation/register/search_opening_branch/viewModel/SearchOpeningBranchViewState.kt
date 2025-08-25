package com.pmb.auth.presentation.register.search_opening_branch.viewModel

import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranch
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranchEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.openAccount.branchName.Branch

data class SearchOpeningBranchViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val headTitle: String? = null,
    val branchList: List<Branch>?=null,
    val searchedBranchList :List<Branch>?=null,
    val selectedBranch: Branch ?=null
) : BaseViewState
