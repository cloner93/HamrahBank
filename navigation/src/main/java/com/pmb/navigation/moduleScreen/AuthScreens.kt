package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class AuthScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object Auth : AuthScreens(route = "auth")
    data object FirstLogin : AuthScreens(route = "first_login")
    data object FirstLoginConfirm : AuthScreens(route = "first_login_confirm")
    data object Login : AuthScreens(route = "login")
    data object Register : AuthScreens(route = "register")
    data object ForgetPassword : AuthScreens(route = "forget_password")
    data object ForgetPasswordAuth : AuthScreens(route = "forget_password_auth")
    data object RegisterNationalId : AuthScreens(route = "register_national_id")
    data object SelectJobInformation :
        AuthScreens(route = "select_job_information")

    data object JobInformation : AuthScreens(route = "job_information")
    data object CheckPostalCode : AuthScreens(route = "check_postal_code")
    data object DepositInformation : AuthScreens(route = "deposit_information")
    data object SearchOpeningBranch :
        AuthScreens(route = "search_opening_branch/{provinceName}/{cityName}/{cityId}") {
        fun createRoute(cityId: Int, cityName: String, provinceName: String) =
            "search_opening_branch/$provinceName/$cityName/$cityId"
    }

    data object Signature : AuthScreens(route = "signature")
    data object Authentication : AuthScreens(route = "authentication")
    data object FacePhotoCapture : AuthScreens(route = "face_photo_capture")
    data object AuthenticationVideo : AuthScreens(route = "authentication_video")
    data object AuthenticationConfirmStep : AuthScreens(route = "authentication_confirm_step")
    data object AuthenticationSelectServices : AuthScreens(route = "authentication_select_services")
    data object FeeDetails : AuthScreens(route = "fee_details")
    data object OpenAccount : AuthScreens(route = "open_account")
    data object ReentryPassword : AuthScreens(route = "reentry_password")
    data object ReentryFaceDetection : AuthScreens(route = "reentry_face_detection")
    data object ChooseAuthenticationType : AuthScreens(route = "choose_authentication_type")
    data object Activation : AuthScreens(route = "activation")
    data object ActivationTaxDetailsScreen : AuthScreens(route = "activation_tax_details")
    data object CardInformation : AuthScreens(route = "card_information")
    data object ScanCard : AuthScreens(route = "scan_card")
    data object CardInformationConfirmation : AuthScreens("card_information_confirmation")
    data object AuthenticationInformation : AuthScreens("authentication_information")
    data object Preparation : AuthScreens("preparation")
}

