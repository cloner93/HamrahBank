package com.pmb.mobile.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.ThemeMode
import com.pmb.domain.theme.usecae.GetThemeModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    initialState: MainActivityViewState,
    private val getThemeUseCase: GetThemeModeUseCase
) : BaseViewModel<MainActivityViewActions, MainActivityViewState, MainActivityViewEvents>(
    initialState
) {

    init {
        loadTheme()
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

    override fun handle(action: MainActivityViewActions) {

    }
}

data class MainActivityViewState(
    val themeMode: ThemeMode = ThemeMode.LIGHT
) : BaseViewState

sealed interface MainActivityViewEvents : BaseViewEvent

sealed interface MainActivityViewActions : BaseViewAction

