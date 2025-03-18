package com.pmb.auth.presentation.register.search_opening_branch.viewModel

import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranch
import com.pmb.core.platform.BaseViewAction

sealed interface SearchOpeningBranchViewActions : BaseViewAction {
    data object ClearAlert : SearchOpeningBranchViewActions
    data object GetOpeningBranchData : SearchOpeningBranchViewActions
    data class SelectOpeningBranchId(val branch: OpeningBranch) : SearchOpeningBranchViewActions
    data class SearchOpeningBranchData(val queryString: String) : SearchOpeningBranchViewActions
    data object ClearSearchData : SearchOpeningBranchViewActions
}