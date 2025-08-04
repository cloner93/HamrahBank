package com.pmb.profile.presentaion.comments_suggestions.viewmodel

import com.pmb.core.platform.BaseViewAction

sealed interface CommentsSuggestionsViewActions : BaseViewAction {
    data object ClearAlert : CommentsSuggestionsViewActions
    data object Submit : CommentsSuggestionsViewActions
    data class UpdateComment(val value: String) : CommentsSuggestionsViewActions
}