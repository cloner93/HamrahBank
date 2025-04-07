package com.pmb.auth.domain.scan_card_info.card_confirmation.entity

data class CardInformationConfirmationEntity(
    val cardOwnerName :String,
    val cardOwnerFamily:String,
    val cardNumber:String,
    val cvv2:String,
    val expirationDate:String
)
