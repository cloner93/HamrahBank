package com.pmb.profile.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubmitCommentsSuggestionsUseCase @Inject constructor(
    private val repository: ProfileRepository
):
BaseUseCase<SubmitCommentsSuggestionsUseCase.Params, Unit>(){
    override suspend fun execute(params: Params): Flow<Result<Unit>> =
        repository.submitCommentsSuggestions(params)


    data class Params(
        val comment: String
    )
}