package com.pmb.mobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pmb.account.presentation.accountScreensHandle
import com.pmb.auth.presentation.authScreensHandle
import com.pmb.ballon.component.base.AppBottomBar
import com.pmb.ballon.component.base.BottomNavItem
import com.pmb.ballon.component.base.bottomNavItems
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.home.presentation.homeScreensHandle
import com.pmb.mobile.presentation.viewmodel.MainActivityViewModel
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens
import com.pmb.navigation.moduleScreen.HomeScreens
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.profile.presentaion.profileScreensHandle
import com.pmb.transfer.presentation.transferScreensHandle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {

            val viewState by viewModel.viewState.collectAsState()

            HamrahBankTheme(themeMode = viewState.themeMode) {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { CheckBottomBar(navController) }) { innerPadding ->
                    AppNavHost(navController = navController, innerPadding = innerPadding)
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    val navigationManager = remember {
        NavigationManager(
            navController = navController,
        )
    }

    var innerBottomPadding = innerPadding.calculateBottomPadding()
//    if (innerBottomPadding != 0.dp) innerBottomPadding -= 16.dp
    CompositionLocalProvider(LocalNavigationManager provides navigationManager) {
        NavHost(
            modifier = Modifier.padding(bottom = innerBottomPadding),
            navController = navigationManager.navController,
            startDestination = navigationManager.getStartDestination().route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            authScreensHandle()
            homeScreensHandle()
            transferScreensHandle()
            accountScreensHandle()
            profileScreensHandle()

        }
    }
}

@Composable
private fun CheckBottomBar(navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val navigationManager = remember {
        NavigationManager(
            navController = navController,
        )
    }
    // Check the current route and determine bottom bar visibility
    val isBottomBarVisible by remember(currentBackStackEntry) {
        derivedStateOf {
            val route = currentBackStackEntry?.destination?.route
            HomeScreens.fromRoute(route) != null ||
                    TransferScreens.fromRoute(route) != null ||
                    AccountScreens.fromRoute(route) != null ||
                    ProfileScreens.fromRoute(route) != null
        }
    }

    if (isBottomBarVisible) {
        CompositionLocalProvider(LocalNavigationManager provides navigationManager) {
            AppBottomBar(tabBarItems = bottomNavItems) { selectedItem ->
                when (selectedItem) {
                    BottomNavItem.Home -> navigationManager.navigateToBottomNavBarScreens(
                        HomeScreens.Home
                    )

                    BottomNavItem.Transfer -> navigationManager.navigateToBottomNavBarScreens(
                        TransferScreens.TransferGraph
                    )

                    BottomNavItem.AccountCard -> navigationManager.navigateToBottomNavBarScreens(
                        AccountScreens.Account
                    )

                    BottomNavItem.Profile -> navigationManager.navigateToBottomNavBarScreens(
                        ProfileScreens.Profile
                    )
                }
            }
        }
    }

}


