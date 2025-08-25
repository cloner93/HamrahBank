package com.pmb.auth.presentation.register.authentication_information.viewModel

import com.pmb.auth.domain.Education
import com.pmb.auth.presentation.register.RegisterSharedViewState
import com.pmb.core.platform.BaseViewAction
import com.pmb.domain.model.openAccount.accountVerifyCode.CityOfBirthInfoDTO

sealed interface AuthenticationInformationViewActions : BaseViewAction {
    data object ClearAlert : AuthenticationInformationViewActions

    data class SetCityId(val city : CityOfBirthInfoDTO):AuthenticationInformationViewActions
    data class SetIdentifyPlaceId(val city :CityOfBirthInfoDTO):AuthenticationInformationViewActions
    data class SetEducation(val eduction : Education):AuthenticationInformationViewActions
    data class SetIssueDate(val issueDateYear: String ,
                            val issueDateMonth: String ,
                            val issueDateDay: String ,):AuthenticationInformationViewActions
    data class SetIssueRegion(val identifyArea :Int):AuthenticationInformationViewActions
    data class SetPhoneNumber(val phoneNumber :String):AuthenticationInformationViewActions
    data class SetAuthenticationData(val sharedViewState: RegisterSharedViewState):
        AuthenticationInformationViewActions

}