package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class HomeScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object HomeGraph: ChargeScreens(route = "home_graph")
    data object Home : HomeScreens(route = "home")

    companion object {
        fun fromRoute(route: String?): HomeScreens? = when (route) {
            Home.route -> Home
            else -> null
        }
    }
}