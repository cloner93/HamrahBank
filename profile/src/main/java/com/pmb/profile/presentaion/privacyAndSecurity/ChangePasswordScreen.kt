package com.pmb.profile.presentaion.privacyAndSecurity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.text_field.AppPasswordTextField
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.navigation.manager.LocalNavigationManager

@Composable
fun ChangePasswordScreen() {

    val navigationManager = LocalNavigationManager.current
    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        topBar = {
            AppTopBar(
                title = "تغییر رمز عبور",
                onBack = {
                    navigationManager.navigateBack()
                })
        },
        footer = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    enable = false, // TODO:
                    title = "ادامه",
                    onClick = {
//                        viewModel.handle(DepositStatementViewActions.Apply)
                    })
            }
        }
    ) {

        AppPasswordTextField(
            value = "",
            label = "رمز عبور فعلی",
            onValidate = {
//                isPassword = it.isValid
            },
            onValueChange = {
//                password = it
            })

        Spacer(modifier = Modifier.height(24.dp))

        AppPasswordTextField(
            value = "",
            label = "رمز عبور جدید",
            conditionMessage = true,
            onValidate = {
//                isPassword = it.isValid
            },
            onValueChange = {
//                password = it
            })

        Spacer(modifier = Modifier.height(24.dp))

        AppPasswordTextField(
            value = "",
            label = "تکرار رمز عبور جدید",
            onValidate = {
//                isPassword = it.isValid
            },
            onValueChange = {
//                password = it
            })
    }
}

@AppPreview
@Composable
private fun ChangePasswordScreenPreview() {
    HamrahBankTheme {
        ChangePasswordScreen()
    }
}