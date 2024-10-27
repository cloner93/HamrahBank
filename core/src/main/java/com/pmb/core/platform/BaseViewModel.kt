package com.pmb.core.platform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<VA : BaseViewAction, VS : BaseViewState, VE : BaseViewEvent>(
    initialState: VS
) : ViewModel() {

    private val _latestEvent: MutableSharedFlow<VE> = MutableSharedFlow()
    val viewEvent: SharedFlow<VE>
        get() = _latestEvent

    // Function to post an event
    protected fun postEvent(event: VE) {
        viewModelScope.launch {
            _latestEvent.emit(event)
        }
    }

    private val _viewState: MutableStateFlow<VS> = MutableStateFlow(initialState)
    val viewState: StateFlow<VS>
        get() = _viewState

    // setState function that derives the current state and updates it with .copy()
    protected fun setState(update: (currentState: VS) -> VS) {
        _viewState.value = update(_viewState.value)
    }


    // Abstract function to handle actions
    abstract fun handle(action: VA)

}

