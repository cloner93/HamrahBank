package com.pmb.facilities.bill.presentation

import com.pmb.core.platform.BaseSharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BillSharedViewModel @Inject constructor(): BaseSharedViewModel<BillSharedState>(
    BillSharedState()
)