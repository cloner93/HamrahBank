package com.pmb.domain.model.openAccount

data class GenerateCodeRequest(
    val nationalCode:String,
    val mobileNo:String,
    val birthDate:String
)
