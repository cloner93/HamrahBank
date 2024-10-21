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
import com.pmb.auth.presentaion.component.ShowChangedNewPasswordBottomSheet
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.TopBar
import com.pmb.ballon.component.text_field.AppPasswordTextField

@Composable
fun NewPasswordScreen(navController: NavController) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var rePassword by remember { mutableStateOf("") }
    var isPassword by remember { mutableStateOf(false) }
    var isRePassword by remember { mutableStateOf(false) }

    AppContent(
        modifier = Modifier.padding(24.dp),
        topBar = TopBar(
            title = stringResource(R.string.select_new_password),
            onBack = { navController.navigateUp() }),
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
                title = stringResource(R.string._continue),
                enable = isPassword && isRePassword && password == rePassword,
                onClick = {
                    showBottomSheet = true
                })
        },
        content = {
            AppPasswordTextField(value = password,
                label = stringResource(R.string.new_password),
                conditionMessage = true,
                onValidate = {
                    isPassword = it.isValid
                },
                onValueChange = { password = it })

            Spacer(modifier = Modifier.height(24.dp))

            AppPasswordTextField(value = rePassword,
                label = stringResource(R.string.re_new_password),
                conditionMessage = true,
                onValidate = {
                    isRePassword = it.isValid
                },
                onValueChange = { rePassword = it })
        })

    if (showBottomSheet) ShowChangedNewPasswordBottomSheet(onDismiss = { showBottomSheet = false })
}