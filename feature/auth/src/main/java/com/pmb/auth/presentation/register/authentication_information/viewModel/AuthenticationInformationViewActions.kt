package com.pmb.auth.presentation.register.authentication_information.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface AuthenticationInformationViewActions : BaseViewAction {
    data object ClearAlert : AuthenticationInformationViewActions
    data object GetAuthenticationEntity : AuthenticationInformationViewActions
    data class SendAuthenticationParams(
        val cityId: Int,
        val identifyId: Int,
        val birthDate: String,
        val identifyArea: String,
        val phoneNumber: String,
        val educationId: Int
    ) : AuthenticationInformationViewActions
    data class SetCityId(val city :String):AuthenticationInformationViewActions
    data class SetIdentifyPlaceId(val city :String):AuthenticationInformationViewActions
    data class SetEducation(val eduction :String):AuthenticationInformationViewActions
    data class SetIdentifyDay(val identifyDay :String):AuthenticationInformationViewActions
    data class SetIdentifyArea(val identifyArea :String):AuthenticationInformationViewActions
    data class SetPhoneNumber(val phoneNumber :String):AuthenticationInformationViewActions

}