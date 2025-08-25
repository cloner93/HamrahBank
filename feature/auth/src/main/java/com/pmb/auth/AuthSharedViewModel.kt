package com.pmb.auth

import com.pmb.core.platform.BaseSharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthSharedViewModel @Inject constructor(

) : BaseSharedViewModel<AuthSharedViewState>(AuthSharedViewState())