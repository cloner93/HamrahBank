package com.pmb.auth.domain.ekyc.fee_details.entity

import com.pmb.core.utils.toCurrency

data class FeeDetailsEntity(
    val isSuccess: Boolean,
    val entityList: List<FeeDetailsObject>,
    val totalPrice: Double
) {
    fun getTotalPrice() = totalPrice.toCurrency()
}

data class FeeDetailsObject(
    val title: String,
    val price: Double,

    ) {
    fun getSeparatedPrice() = price.toCurrency()
}
