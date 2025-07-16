package com.pmb.auth.presentation.register.account_opening.viewModel

import com.pmb.calender.Jdn
import com.pmb.calender.utils.Calendar
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import io.github.persiancalendar.calendar.PersianDate

data class OpeningAccountViewState(
    val isLoading : Boolean= false,
    val alertModelState: AlertModelState? = null,
    val phoneNumber: String? = null,
    val nationalId: String? = null,
    val birthDay: PersianDate ?=null,
    val isShowingBottomSheet: Boolean = false,
) : BaseViewState