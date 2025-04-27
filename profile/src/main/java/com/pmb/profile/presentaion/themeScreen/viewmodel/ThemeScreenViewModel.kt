package com.pmb.profile.presentaion.themeScreen.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeScreenViewModel @Inject constructor(
    initialState: ThemeScreenViewState,
//    private val getThemeUseCase: GetThemeUseCase,
) : BaseViewModel<ThemeScreenViewActions, ThemeScreenViewState, ThemeScreenViewEvents>(initialState) {
    override fun handle(action: ThemeScreenViewActions) {
//        TODO("Not yet implemented")
    }
}


sealed interface ThemeScreenViewEvents : BaseViewEvent {
    data class ShowError(val message: String) : ThemeScreenViewEvents
}

sealed interface ThemeScreenViewActions : BaseViewAction
data class ThemeScreenViewState(
    val isLoading: Boolean = false,
) : BaseViewState
