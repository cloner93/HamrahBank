package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class AuthScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object AuthGraph : AuthScreens(route = "auth_graph")
    data object Auth : AuthScreens(route = "auth")
    data object FirstLogin : AuthScreens(route = "first_login")
    data object FirstLoginConfirm :
        AuthScreens(route = "first_login_confirm/{mobileNumber}/{username}/{password}") {
        fun createRoute(mobileNumber: String, username: String, password: String) =
            "first_login_confirm/$mobileNumber/$username/$password"
    }

    data object Login : AuthScreens(route = "login")
    data object EnableFingerprint : AuthScreens(route = "enable_fingerprint")

    data object ForgetPassword : AuthScreens(route = "forget_password")
    data object ForgetPasswordAuth : AuthScreens(route = "forget_password_auth")
    data object AuthenticationConfirmStep : AuthScreens(route = "authentication_confirm_step")

    data object OpenAccount : AuthScreens(route = "open_account")
    data object ReentryPassword : AuthScreens(route = "reentry_password")
    data object ReentryFaceDetection : AuthScreens(route = "reentry_face_detection")
    data object ChooseAuthenticationType : ActivationScreens(route = "choose_authentication_type")

}

sealed class ActivationScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object ActivationGraph : EKYCScreens("activation_graph")

    data object Activation : ActivationScreens(route = "activation")
    data object ActivationTaxDetailsScreen : ActivationScreens(route = "activation_tax_details")
}

sealed class EKYCScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object EKYCGraph : EKYCScreens("ekyc_graph")
    data object EKYCRegisterNationalId : EKYCScreens(route = "ekyc_register_national_id")
    data object EKYCAuthentication : EKYCScreens(route = "ekyc_authentication")
    data object EKYCFacePhotoCapture : EKYCScreens(route = "ekyc_face_photo_capture")
    data object EKYCVideoCapture : EKYCScreens(route = "ekyc_video_capture")
}

object SharedAuthAndActivationScopeGraph :
    Screen(baseRoute = "shared_auth_activation_scope_graph", arguments = emptyMap())

sealed class CardScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object CardGraph : CardScreens("card_graph")
    data object CardInformation : CardScreens(route = "card_information")
    data object ScanCard : CardScreens(route = "scan_card")
    data object CardInformationConfirmation : CardScreens("card_information_confirmation")
}

sealed class RegisterScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object RegisterGraph : RegisterScreens("register_graph")
    data object Preparation : RegisterScreens("preparation")
    data object Register : RegisterScreens(route = "register")
    data object RegisterNationalId : RegisterScreens(route = "register_national_id")
    data object RegisterConfirm : RegisterScreens(route = "register_confirm")
    data object AuthenticationInformation : RegisterScreens("authentication_information")
    data object JobInformation : RegisterScreens(route = "job_information")
    data object SelectJobInformation : RegisterScreens(route = "select_job_information")
    data object CheckPostalCode : RegisterScreens(route = "check_postal_code")
    data object DepositInformation : RegisterScreens(route = "deposit_information")
    data object SearchOpeningBranch :
        RegisterScreens(route = "search_opening_branch/{provinceCode}/{provinceName}/{cityName}/{cityId}") {
        fun createRoute(cityId: Int, cityName: String, provinceCode: Int,provinceName:String) =
            "search_opening_branch/$provinceCode/$provinceName/$cityName/$cityId"
    }

    data object Signature : RegisterScreens(route = "signature")
    data object AuthenticationSelectServices :
        RegisterScreens(route = "authentication_select_services")

    data object RegisterChooseCard : RegisterScreens(route = "register_choose_card")
    data object RegisterAuthentication : RegisterScreens(route = "register_authentication")
    data object RegisterFacePhotoCapture : RegisterScreens(route = "register_face_photo_capture")
    data object RegisterVideo : RegisterScreens(route = "register_video")
    data object RegisterConfirmStep : RegisterScreens(route = "register_confirm_step")
    data object FeeDetails : RegisterScreens(route = "fee_details")
    data object SelectCity : RegisterScreens(route = "select_city/{componentName}/{listId}") {
        fun createRoute(componentName: String, listId: String) =
            "select_city/$componentName/$listId"
    }
}
