package com.pmb.auth.domain.register.authentication_information.entity

data class GetAuthenticationEntity(
    val isSuccess :Boolean,
    val cities :List<City>,
    val identifyPlace :List<City>,
    val educations :List<Education>

)
data class City(
    val id: Int,
    val city: String
)
data class Education(
    val id: Int,
    val education: String
)