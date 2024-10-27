package com.pmb.mobile.presentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pmb.auth.presentaion.foget_password.ForgetPasswordAuthScreen
import com.pmb.auth.presentaion.intro.IntroScreen
import com.pmb.auth.presentaion.login.LoginScreen
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.mobile.presentaion.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                HamrahBankTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        val navController = rememberNavController()
                        val myViewModel: MainActivityViewModel = hiltViewModel()
                        NavHost(
                            navController = navController,
                            startDestination = "start",
                            Modifier.padding(innerPadding)
                        ) {
                            composable(route = "start") {
                                IntroScreen(
                                    navController = navController,
                                    modifier = Modifier.padding(innerPadding),
                                )
                            }

                            composable(route = "login") {
                                LoginScreen(navController = navController)
                            }

                            composable(route = "register") {

                            }

                            composable(route = "forget_password") {
//                                ForgetPasswordScreen(navController = navController)
                                ForgetPasswordAuthScreen(navController = navController)
                            }
                        }

                    }
                }
            }
        }
    }
}