package com.pmb.auth.domain.ekyc.authenticationSelectServices.entity

import androidx.compose.runtime.MutableState
import com.pmb.core.utils.toCurrency

data class SelectServicesEntity(
    val isSuccess: Boolean,
    val selectServicesList: MutableList<SelectServicesObject>
)

data class SelectServicesObject(
    val id: Int,
    val title: String,
    var isChecked: MutableState<Boolean>,
    val price: Double
) {
    fun getSeparatedPrice() = price.toCurrency()
}

data class SelectServicesParams(
    val ids: List<Int>
)

data class ConfirmSelectServicesEntity(
    val isSuccess: Boolean,
)