package com.pmb.core.platform

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


open class BaseSharedViewModel<T : BaseSharedState>(initialState: T) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<T> = _state

    fun updateState(update: T.() -> T) {
        _state.value = _state.value.update()
    }
}