package com.pmb.profile.presentaion.comments_suggestions.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class CommentsSuggestionsViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val comment: String = "",
    val submitted: Boolean = false,
) : BaseViewState {
    val buttonEnabled: Boolean
        get() = comment.isNotBlank() && !loading || submitted
}
