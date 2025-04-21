package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class ProfileScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object Profile : ProfileScreens(route = "profile")
    data object PersonalInfo : ProfileScreens(route = "profile_personal_info")
    data object ChangeUsername : ProfileScreens(route = "profile_change_username")
    data object ChangePhoneNumber : ProfileScreens(route = "profile_change_phone_number")
    data object ChangeAddress : ProfileScreens(route = "profile_change_address")
    data object ChangeJob : ProfileScreens(route = "profile_change_job")
    data object ChangeEducation : ProfileScreens(route = "profile_change_education")

    companion object {
        fun fromRoute(route: String?): ProfileScreens? = when (route) {
            Profile.route -> Profile
            else -> null
        }
    }
}