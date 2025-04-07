package com.pmb.auth.domain.register.deposit_information.entity

data class SendDepositInformationEntity(
    val isSuccess: Boolean,
)

data class SendDepositInformationParams(
    val depositType: Int?,
    val branchProvince: Int?,
    val branchCity: Int?,
    val openingBranch: Int?,
)