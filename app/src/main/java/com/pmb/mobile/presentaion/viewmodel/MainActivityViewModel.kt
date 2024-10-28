package com.pmb.mobile.presentaion.viewmodel

import android.util.Log
import com.pmb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    initialState:MainActivityViewState
) :BaseViewModel<MainActivityViewActions,MainActivityViewState,MainActivityViewEvents>(initialState) {

    init {
        Log.d("Masoud Tag", ": ${initialState.name}")
        setState { state ->
            state.copy(name = "masoud")
        }

    }
    override fun handle(action: MainActivityViewActions) {

    }
}