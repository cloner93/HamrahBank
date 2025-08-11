package com.pmb.mobile.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.pmb.account.presentation.accountScreensHandle
import com.pmb.auth.presentation.authScreensHandle
import com.pmb.ballon.component.base.AppBottomBar
import com.pmb.ballon.component.base.AppSnackBar
import com.pmb.ballon.component.base.BottomNavItem
import com.pmb.ballon.component.base.bottomNavItems
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.core.composition.LocalScreenScope
import com.pmb.core.composition.LocalSnackbarHostState
import com.pmb.home.presentation.homeScreensHandle
import com.pmb.mobile.presentation.viewmodel.MainActivityViewModel
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens
import com.pmb.navigation.moduleScreen.AuthScreens
import com.pmb.navigation.moduleScreen.HomeScreens
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.navigation.moduleScreen.SharedAuthAndActivationScopeGraph
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.profile.presentaion.profileScreensHandle
import com.pmb.transfer.presentation.transferScreensHandle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val viewState by viewModel.viewState.collectAsState()

            val navController = rememberNavController()
            val navigationManager = remember { NavigationManager(navController) }
            val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            HamrahBankTheme(themeMode = viewState.themeMode) {
                CompositionLocalProvider(
                    LocalNavigationManager provides navigationManager,
                    LocalSnackbarHostState provides snackbarHostState,
                    LocalScreenScope provides scope
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = {
                            SnackbarHost(
                                hostState = snackbarHostState,
                                snackbar = { data ->
                                    AppSnackBar(data)
                                },
                            )
                        },
                        bottomBar = { CheckBottomBar(navController) }
                    ) { innerPadding ->
                        AppNavHost(innerPadding)
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavHost(innerPadding: PaddingValues) {
    val navigationManager = LocalNavigationManager.current

    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navigationManager.navController,
        startDestination = navigationManager.getStartDestination().route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        navigation(
            route = SharedAuthAndActivationScopeGraph.route,
            startDestination = AuthScreens.AuthGraph.route
        ) {
            authScreensHandle()
        }
        homeScreensHandle()
        transferScreensHandle()
        accountScreensHandle()
        profileScreensHandle()
    }
}

@Composable
private fun CheckBottomBar(navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route.orEmpty()

    val selectedItem = bottomNavItems.firstOrNull {
        when (it) {
            BottomNavItem.Home -> HomeScreens.fromRoute(currentRoute) != null
            BottomNavItem.Transfer -> TransferScreens.fromRoute(currentRoute) != null
            BottomNavItem.AccountCard -> AccountScreens.fromRoute(currentRoute) != null
            BottomNavItem.Profile -> ProfileScreens.fromRoute(currentRoute) != null
        }
    }

    val navigationManager = LocalNavigationManager.current

    if (selectedItem != null) {
        AppBottomBar(
            tabBarItems = bottomNavItems,
            selectedTab = selectedItem
        ) { selected ->
            when (selected) {
                BottomNavItem.Home -> navigationManager.navigateToBottomNavBarScreens(HomeScreens.Home)
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