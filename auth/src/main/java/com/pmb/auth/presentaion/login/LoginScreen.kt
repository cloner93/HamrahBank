package com.pmb.auth.presentaion.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.presentaion.AuthScreens
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppSingleTextField
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.text_field.AppPasswordTextField
import com.pmb.core.presentation.NavigationManager
import com.pmb.home.presentation.HomeScreens

@Composable
fun LoginScreen(navigationManager: NavigationManager) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
fun LoginScreen(navController: NavController) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(56.dp),
            painter = painterResource(R.drawable.img_mellat_logo),
            contentDescription = "mellat logo"
        )

        Spacer(modifier = Modifier.size(32.dp))


        AppSingleTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            label = stringResource(com.pmb.auth.R.string.username),
            onValueChange = { username = it },
        )

        Spacer(modifier = Modifier.size(32.dp))

        AppPasswordTextField(modifier = Modifier.fillMaxWidth(),
            value = password,
            label = stringResource(com.pmb.auth.R.string.password),
            onValueChange = { password = it })

        Spacer(modifier = Modifier.size(32.dp))

        AppButton(modifier = Modifier.fillMaxWidth(),
            title = stringResource(com.pmb.auth.R.string.login),
            onClick = { navigationManager.navigate(HomeScreens.Home) })

        Spacer(modifier = Modifier.size(8.dp))

        AppTextButton(modifier = Modifier.fillMaxWidth(),
            title = stringResource(com.pmb.auth.R.string.forget_password),
            onClick = {
                navigationManager.navigate(AuthScreens.ForgetPassword)
            })
    }
}