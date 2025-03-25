package com.pmb.auth.domain.scan_card_info.card_info.entity

data class CardInfoEntity(
    val isSuccess:Boolean
)
data class CardInfoParams(
    val cardNumber:String,
    val cvv2 :String,
    val year:String,
    val month :String,
    val password :String
)
