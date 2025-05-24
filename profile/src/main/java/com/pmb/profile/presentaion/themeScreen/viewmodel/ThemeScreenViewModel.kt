package com.pmb.profile.presentaion.themeScreen.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeScreenViewModel @Inject constructor(
    initialState: ThemeScreenViewState,
//    private val getThemeUseCase: GetThemeUseCase,
) : BaseViewModel<ThemeScreenViewActions, ThemeScreenViewState, ThemeScreenViewEvents>(initialState) {
    override fun handle(action: ThemeScreenViewActions) {
        when (action) {
            ThemeScreenViewActions.SelectSystemTheme -> updateTheme(ThemeMode.SYSTEM)
            ThemeScreenViewActions.SelectLightTheme -> updateTheme(ThemeMode.LIGHT)
            ThemeScreenViewActions.SelectDarkTheme -> updateTheme(ThemeMode.DARK)
        }
    }


    private fun updateTheme(mode: ThemeMode) {
        setState { it.copy(themeMode = it.themeMode) }
    }
}


sealed interface ThemeScreenViewEvents : BaseViewEvent {
    data class ShowError(val message: String) : ThemeScreenViewEvents
}

sealed interface ThemeScreenViewActions : BaseViewAction {
    object SelectSystemTheme : ThemeScreenViewActions
    object SelectLightTheme : ThemeScreenViewActions
    object SelectDarkTheme : ThemeScreenViewActions
}

data class ThemeScreenViewState(
    val isLoading: Boolean = false, val themeMode: ThemeMode = ThemeMode.LIGHT
) : BaseViewState
