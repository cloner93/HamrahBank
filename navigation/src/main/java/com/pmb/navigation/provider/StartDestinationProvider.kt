package com.pmb.navigation.provider

import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.navigation.screen.Screen

interface NavigationStartDestinationProvider {
    fun getStartDestination(): Screen
}
object DefaultStartDestinationProvider : NavigationStartDestinationProvider {
    override fun getStartDestination(): Screen {
        return ProfileScreens.Profile
    }
}