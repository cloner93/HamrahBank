package com.pmb.mobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pmb.account.presentation.AccountScreens
import com.pmb.account.presentation.accountScreensHandle
import com.pmb.auth.presentation.AuthScreens
import com.pmb.auth.presentation.authScreensHandle
import com.pmb.ballon.component.base.AppBottomBar
import com.pmb.ballon.component.base.BottomNavItem
import com.pmb.ballon.component.base.bottomNavItems
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.home.presentation.HomeScreens
import com.pmb.home.presentation.homeScreensHandle
import com.pmb.profile.presentaion.ProfileScreens
import com.pmb.profile.presentaion.profileScreensHandle
import com.pmb.transfer.presentation.TransferScreens
import com.pmb.transfer.presentation.transferScreensHandle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                HamrahBankTheme {
                    val navController = rememberNavController()

                    Scaffold(modifier = Modifier.fillMaxSize(),
                        bottomBar = { CheckBottomBar(navController) }) { innerPadding ->
                        AppNavHost(navController = navController, innerPadding = innerPadding)
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    val navigationManager by remember {
        mutableStateOf(
            NavigationManager(
                navController = navController,
                startDestination = AuthScreens.ReentryPassword
            )
        )
    }

    NavHost(
        navController = navigationManager.navController,
        startDestination = navigationManager.startDestination.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        authScreensHandle(navigationManager = navigationManager)
        homeScreensHandle(navigationManager = navigationManager)
        transferScreensHandle(navigationManager = navigationManager)
        accountScreensHandle(navigationManager = navigationManager)
        profileScreensHandle(navigationManager = navigationManager)
    }
}

@Composable
private fun CheckBottomBar(navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val navigationManager by remember {
        mutableStateOf(
            NavigationManager(
                navController = navController,
                startDestination = AuthScreens.Auth
            )
        )
    }

    // Check the current route and determine bottom bar visibility
    val isBottomBarVisible by remember {
        derivedStateOf {
            val route = currentBackStackEntry?.destination?.route
            val homeScreen = HomeScreens.fromRoute(route)
            val transferScreen = TransferScreens.fromRoute(route)
            val accountScreen = AccountScreens.fromRoute(route)
            val profileScreen = ProfileScreens.fromRoute(route)
            homeScreen != null || accountScreen != null || profileScreen != null || transferScreen != null
        }
    }

    if (isBottomBarVisible) {
        AppBottomBar(tabBarItems = bottomNavItems) { selectedItem ->
            when (selectedItem) {
                BottomNavItem.Home -> navigationManager.navigate(HomeScreens.Home)
                BottomNavItem.Transfer -> navigationManager.navigate(TransferScreens.Transfer)
                BottomNavItem.AccountCard -> navigationManager.navigate(AccountScreens.Account)
                BottomNavItem.Profile -> navigationManager.navigate(ProfileScreens.Profile)
            }
        }
    }
}


