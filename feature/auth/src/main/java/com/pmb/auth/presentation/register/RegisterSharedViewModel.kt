package com.pmb.auth.presentation.register

import com.pmb.core.platform.BaseSharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterSharedViewModel @Inject constructor(

) : BaseSharedViewModel<RegisterSharedViewState>(RegisterSharedViewState())