package com.pmb.auth.domain.register.deposit_information.entity

data class BranchCityEntity(
    val isSuccess: Boolean,
    val branchCity: List<BranchCity>
)

data class BranchCity(
    val id: Int,
    val city: String
)

data class BranchCityParams(
    val id: Int
)