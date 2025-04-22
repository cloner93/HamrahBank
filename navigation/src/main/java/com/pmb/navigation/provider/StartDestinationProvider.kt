package com.pmb.navigation.provider

import com.pmb.navigation.moduleScreen.AuthScreens
import com.pmb.navigation.moduleScreen.HomeScreens
import com.pmb.navigation.screen.Screen

interface NavigationStartDestinationProvider {
    fun getStartDestination(): Screen
}
object DefaultStartDestinationProvider : NavigationStartDestinationProvider {
    override fun getStartDestination(): Screen {
        return HomeScreens.Home
    }
}