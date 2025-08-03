package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class ProfileScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object Profile : ProfileScreens(route = "profile")
    data object ThemeScreen : ProfileScreens(route = "themeScreen")

    sealed class PersonalInfo(route: String) : ProfileScreens(route) {
        data object Graph : PersonalInfo(route = "profile_personal_info_graph")

        data object Info : PersonalInfo(route = "profile_personal_info")
        data object ChangeUsername : PersonalInfo(route = "profile_change_username")
        data object ChangePhoneNumber : PersonalInfo(route = "profile_change_phone_number")
        data object VerifyPhoneNumberOtp : PersonalInfo(route = "profile_verify_phone_number")
        data object ChangeAddress : PersonalInfo(route = "profile_change_address")
        data object ChangeJob : PersonalInfo(route = "profile_change_job")
        data object SelectJob : PersonalInfo(route = "profile_select_job")
        data object ChangeEducation : PersonalInfo(route = "profile_change_education")
    }

    sealed class PrivacyAndSecurity(route: String) : ProfileScreens(route) {
        data object Graph : PrivacyAndSecurity(route = "profile_privacy_and_security_graph")

        data object PrivacySecurityScreen : PrivacyAndSecurity(route = "profile_privacy_security")
        data object ChangePasswordScreen : PrivacyAndSecurity(route = "profile_change_password")
        data object EnableFingerprintScreen :
            PrivacyAndSecurity(route = "enable_fingerprint_screen")

    }

    sealed class Update(route: String) : ProfileScreens(route) {
        data object Graph : PersonalInfo(route = "profile_update_graph")

        data object Status : PersonalInfo(route = "profile_update_status")
        data object Latest : PersonalInfo(route = "profile_update_latest")
        data object Detail : PersonalInfo(route = "profile_update_detail")
    }

    companion object {
        fun fromRoute(route: String?): ProfileScreens? = when (route) {
            Profile.route -> Profile
            else -> null
        }
    }
}