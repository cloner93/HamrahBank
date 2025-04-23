package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class ProfileScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object Profile : ProfileScreens(route = "profile")
    data object ThemeScreen : ProfileScreens(route = "themeScreen")

    companion object {
        fun fromRoute(route: String?): ProfileScreens? = when (route) {
            Profile.route -> Profile
            else -> null
        }
    }
}