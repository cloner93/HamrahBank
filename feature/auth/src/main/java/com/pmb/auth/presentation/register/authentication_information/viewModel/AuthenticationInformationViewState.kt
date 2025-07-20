package com.pmb.auth.presentation.register.authentication_information.viewModel

import com.pmb.auth.domain.Education
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.openAccount.accountVerifyCode.CityOfBirthInfoDTO

data class AuthenticationInformationViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val birthDatePlace: CityOfBirthInfoDTO? = null,
    val issuePlace: CityOfBirthInfoDTO? = null,
    val issueDateYear: String? = null ,
    val issueDateMonth: String ? = null,
    val issueDateDay: String? = null ,
    val issueCode: Int? = null,
    val tel: String? = null,
    val education: Education? = null
) : BaseViewState