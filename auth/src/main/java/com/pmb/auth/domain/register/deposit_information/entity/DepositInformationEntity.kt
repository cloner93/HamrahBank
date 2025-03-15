package com.pmb.auth.domain.register.deposit_information.entity

data class DepositInformationEntity(
    val isSuccess: Boolean,
    val depositType: List<DepositType>,
    val branchProvince: List<BranchProvince>,
)

data class DepositType(
    val id: Int,
    val type: String
)

data class BranchProvince(
    val id: Int,
    val province: String
)
