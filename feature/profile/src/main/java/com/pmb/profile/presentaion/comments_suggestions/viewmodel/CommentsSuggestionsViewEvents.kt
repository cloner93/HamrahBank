package com.pmb.profile.presentaion.comments_suggestions.viewmodel

import com.pmb.core.platform.BaseViewEvent

sealed interface CommentsSuggestionsViewEvents : BaseViewEvent {
    data object NavigateBack : CommentsSuggestionsViewEvents
}