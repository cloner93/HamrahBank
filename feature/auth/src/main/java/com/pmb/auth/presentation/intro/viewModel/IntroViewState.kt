package com.pmb.auth.presentation.intro.viewModel

import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.UserData

data class IntroViewState(
    val userData: UserData ?=null
) : BaseViewState
