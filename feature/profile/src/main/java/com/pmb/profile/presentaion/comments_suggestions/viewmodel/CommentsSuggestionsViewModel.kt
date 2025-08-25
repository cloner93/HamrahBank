package com.pmb.profile.presentaion.comments_suggestions.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.profile.domain.use_case.SubmitCommentsSuggestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsSuggestionsViewModel @Inject constructor(
    private val submitCommentsSuggestionsUseCase: SubmitCommentsSuggestionsUseCase
) :
    BaseViewModel<CommentsSuggestionsViewActions, CommentsSuggestionsViewState, CommentsSuggestionsViewEvents>(
        CommentsSuggestionsViewState()
    ) {
    override fun handle(action: CommentsSuggestionsViewActions) {
        when (action) {
            CommentsSuggestionsViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            CommentsSuggestionsViewActions.Submit -> handleSubmit()
            is CommentsSuggestionsViewActions.UpdateComment ->
                setState { it.copy(comment = action.value) }
        }
    }

    private fun handleSubmit() {
        if (viewState.value.submitted) {
            postEvent(CommentsSuggestionsViewEvents.NavigateBack)
        } else {
            submitCommentsSuggestions()
        }
    }

    private fun submitCommentsSuggestions() {
        viewModelScope.launch {
            submitCommentsSuggestionsUseCase.invoke(SubmitCommentsSuggestionsUseCase.Params(comment = viewState.value.comment))
                .collect { result ->
                    when (result) {
                        is Result.Error ->
                            setState {
                                it.copy(
                                    loading = false,
                                    alertState = AlertModelState.Dialog(
                                        title = "خطا",
                                        description = " ${result.message}",
                                        positiveButtonTitle = "تایید",
                                        onPositiveClick = {
                                            setState { state -> state.copy(alertState = null) }
                                        }
                                    )
                                )
                            }

                        Result.Loading ->
                            setState {
                                it.copy(loading = true)
                            }

                        is Result.Success -> {
                            setState {
                                it.copy(
                                    loading = false,
                                    submitted = true
                                )
                            }
                        }
                    }
                }
        }
    }
}