package com.pmb.mobile.presentaion

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pmb.account.presentation.accountScreensHandle
import com.pmb.auth.presentaion.AuthScreens
import com.pmb.auth.presentaion.authScreensHandle
import com.pmb.ballon.component.base.AppBottomBar
import com.pmb.ballon.component.base.bottomNavItems
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.home.presentation.homeScreensHandle
import com.pmb.profile.presentaion.profileScreensHandle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                HamrahBankTheme {
                    Scaffold(modifier = Modifier.fillMaxSize(),
                        bottomBar = {

                        }) { innerPadding ->
                        AppNavHost(innerPadding)
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavHost(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    val navigationManager by remember {
        mutableStateOf(
            NavigationManager(
                navController = navController,
                startDestination = AuthScreens.Auth
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
        accountScreensHandle(navigationManager = navigationManager)
        profileScreensHandle(navigationManager = navigationManager)
    }
}


