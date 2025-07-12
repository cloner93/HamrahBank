package com.pmb.facilities.charge.presentation

import com.pmb.core.platform.BaseSharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChargeSharedViewModel @Inject constructor(

) : BaseSharedViewModel<ChargeSharedState>(ChargeSharedState())