package com.pmb.facilities.charge.presentation.charge

import com.pmb.core.platform.BaseViewState
import com.pmb.facilities.R

data class ChargeViewState(
    val isLoading: Boolean = false,
    val selectedSim:String = "",
    val simCartList:List<ChargeData> = listOf(
        ChargeData(
            id = 0,
            imageString = R.drawable.ic_irancell,
            operator = "ایرانسل",
            phoneNumber = "۰۹۹۱۱۰۵۱۷۲۵"
        ),
        ChargeData(
            id = 1,
            imageString = R.drawable.ic_irancell,
            operator = "ایرانسل",
            phoneNumber = "۰۹۹۲۴۹۲۰۷۹۰"
        ),
        ChargeData(
            id = 2,
            imageString = R.drawable.ic_irancell,
            operator = "ایرانسل",
            phoneNumber = "09308160417"
        ),
    )
) : BaseViewState