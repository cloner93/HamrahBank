package com.pmb.core.platform

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Stack

abstract class BaseParentViewModel<VS : BaseViewState, SS : BaseChildScreenState> (
    initialViewState: VS,
    initialScreenState:SS
) : ViewModel() {

    private val _viewState: MutableStateFlow<VS> = MutableStateFlow(initialViewState)
    val viewState: StateFlow<VS>
        get() = _viewState

    private val _screenState: MutableStateFlow<SS?> = MutableStateFlow(initialScreenState)
    val screenState: StateFlow<SS?>
        get() = _screenState

    private val screenStateStack: Stack<SS> = Stack()

    // Set view state using a copy function
    protected fun setState(update: (currentState: VS) -> VS) {
        _viewState.value = update(_viewState.value)
    }

    // Push a new screen state to the stack and update screen state
    protected fun setScreenState(screenState: SS) {
        screenStateStack.push(screenState)
        _screenState.value = screenState
    }

    // Pop the top screen state and update screen state to the latest state or null
    protected fun popStackEntry() {
        if (screenStateStack.isNotEmpty()) {
            screenStateStack.pop()
        }
        _screenState.value = if (screenStateStack.isNotEmpty()) screenStateStack.peek() else null
    }
}