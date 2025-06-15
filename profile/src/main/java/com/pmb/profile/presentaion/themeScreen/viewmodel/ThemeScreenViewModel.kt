package com.pmb.profile.presentaion.themeScreen.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.ThemeMode
import com.pmb.domain.usecae.theme.GetThemeModeUseCase
import com.pmb.domain.usecae.theme.SaveThemeModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeScreenViewModel @Inject constructor(
    initialState: ThemeScreenViewState,
    private val getThemeUseCase: GetThemeModeUseCase,
    private val saveThemeUseCase: SaveThemeModeUseCase
) :
    BaseViewModel<ThemeScreenViewActions, ThemeScreenViewState, ThemeScreenViewEvents>(initialState) {

    init {
        loadTheme()
    }

    override fun handle(action: ThemeScreenViewActions) {
        when (action) {
            is ThemeScreenViewActions.SelectTheme -> {
                setState { it.copy(themeMode = action.mode) }
                saveSelectedTheme(action.mode)

            }
        }
    }

    private fun loadTheme() {
        viewModelScope.launch {
            getThemeUseCase().collect { theme ->
                setState {
                    it.copy(
                        themeMode = theme
                    )
                }
            }
        }
    }

    private fun saveSelectedTheme(mode: ThemeMode) {
        viewModelScope.launch {
            saveThemeUseCase.invoke(mode)
        }
    }
}


sealed interface ThemeScreenViewEvents : BaseViewEvent {
    data class ShowError(val message: String) : ThemeScreenViewEvents
}

sealed interface ThemeScreenViewActions : BaseViewAction {
    class SelectTheme(val mode: ThemeMode) : ThemeScreenViewActions

}

data class ThemeScreenViewState(
    val isLoading: Boolean = false,
    val themeMode: ThemeMode = ThemeMode.SYSTEM
) : BaseViewState
