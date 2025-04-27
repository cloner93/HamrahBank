package com.pmb.facilities.charge.presentation.buying_charge

import com.pmb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BuyingChargeViewModel @Inject constructor(

) : BaseViewModel<BuyingChargeViewActions, BuyingChargeViewState, BuyingChargeViewEvents>(
    BuyingChargeViewState()
) {

    override fun handle(action: BuyingChargeViewActions) {

    }
}