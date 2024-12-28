package com.pmb.auth.presentaion.new_password

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pmb.auth.R
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppOutlineButton
import com.pmb.ballon.component.base.AppSingleTextField
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.TopBar
import com.pmb.ballon.component.text_field.AppPasswordTextField
import com.pmb.core.utils.isMobile

@Composable
fun NewPasswordIntroScreen(navController: NavController) {
    var phonenumber by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPhoneNumber by remember { mutableStateOf(false) }
    var isUsername by remember { mutableStateOf(false) }
    var isPassword by remember { mutableStateOf(false) }

    AppContent(
        modifier = Modifier.padding(24.dp),
        topBar = TopBar(
            title = stringResource(R.string.select_new_password),
            onBack = { navController.navigateUp() }),
        content = {
            AppNumberTextField(
                value = phonenumber,
                label = stringResource(R.string.phone_number),
                onValueChange = {
                    phonenumber = it
                    isPhoneNumber = it.isMobile().isValid
                })
            Spacer(modifier = Modifier.height(24.dp))
            AppSingleTextField(
                value = username,
                label = stringResource(R.string.username),
                onValueChange = {
                    isUsername = it.isNotEmpty()
                    username = it
                })
            Spacer(modifier = Modifier.height(24.dp))
            AppPasswordTextField(value = password,
                label = stringResource(R.string.password),
                onValidate = {
                    isPassword = it.isValid
                },
                onValueChange = { password = it })

            Spacer(modifier = Modifier.height(32.dp))


            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
                title = stringResource(R.string._continue),
                enable = isPassword && isPhoneNumber && isUsername,
                onClick = {

                })
            Spacer(modifier = Modifier.height(8.dp))
            AppTextButton(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.forget_password),
                onClick = {

                })
            Spacer(modifier = Modifier.weight(1f))
            AppOutlineButton(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.hamrah_bank_activate),
                onClick = {

                })
        })
}