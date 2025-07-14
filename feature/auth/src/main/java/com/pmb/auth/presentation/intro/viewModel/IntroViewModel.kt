package com.pmb.auth.presentation.intro.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) : BaseViewModel<IntroViewActions, IntroViewState, BaseViewEvent>(
    IntroViewState(
    )
) {
    init {
        handle(IntroViewActions.GetUserData)
    }
    override fun handle(action: IntroViewActions) {
        when (action) {
            is IntroViewActions.GetUserData -> {
                getUserData()
            }

        }
    }

    private fun getUserData() {
        viewModelScope.launch {
            getUserDataUseCase.invoke().collectLatest { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(userData = result.data)
                        }
                    }

                    else -> {

                    }
                }
            }
        }
    }

}