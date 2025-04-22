package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class ProfileScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object Profile : ProfileScreens(route = "profile")

    sealed class PersonalInfo(route: String) : ProfileScreens(route) {
        data object Graph : PersonalInfo(route = "profile_personal_info_graph")

        data object Info : PersonalInfo(route = "profile_personal_info")
        data object ChangeUsername : PersonalInfo(route = "profile_change_username")
        data object ChangePhoneNumber : PersonalInfo(route = "profile_change_phone_number")
        data object VerifyPhoneNumberOtp : PersonalInfo(route = "profile_verify_phone_number")
        data object ChangeAddress : PersonalInfo(route = "profile_change_address")
        data object ChangeJob : PersonalInfo(route = "profile_change_job")
        data object ChangeEducation : PersonalInfo(route = "profile_change_education")
    }


    companion object {
        fun fromRoute(route: String?): ProfileScreens? = when (route) {
            Profile.route -> Profile
            else -> null
        }
    }
}