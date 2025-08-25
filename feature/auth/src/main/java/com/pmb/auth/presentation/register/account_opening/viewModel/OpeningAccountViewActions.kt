package com.pmb.auth.presentation.register.account_opening.viewModel

import com.pmb.core.platform.BaseViewAction
import io.github.persiancalendar.calendar.PersianDate

sealed interface OpeningAccountViewActions : BaseViewAction {
    data object ClearAlertModelState : OpeningAccountViewActions
    data class SetPhoneNumber(val phoneNumber: String) : OpeningAccountViewActions
    data class SetNationalId(val nationalId: String) : OpeningAccountViewActions
    data class SetBirthday(  val birthDateYear: String ,
                             val birthDateMonth: String ,
                             val birthDateDay: String ,) : OpeningAccountViewActions
    data class ShowBottomSheet(val isVisible: Boolean) : OpeningAccountViewActions
    data object SendOpeningAccountData : OpeningAccountViewActions
}