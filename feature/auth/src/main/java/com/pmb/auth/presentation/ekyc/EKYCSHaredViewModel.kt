package com.pmb.auth.presentation.ekyc

import com.pmb.core.platform.BaseSharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EKYCSHaredViewModel @Inject constructor(

) : BaseSharedViewModel<EKYCSharedViewState>(EKYCSharedViewState())