package com.pmb.auth.presentaion.ekyc.openAccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentaion.ekyc.authenticationSelectServices.RoundedCornerCheckbox
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.utils.toCurrency

@Composable
fun OpenAccountScreen(navigationManager: NavigationManager) {
    var checked by remember { mutableStateOf(false) }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.open_account),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = checked,
                title = stringResource(R.string.confirm_open),
                onClick = {
                })
        }
    ) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(com.pmb.ballon.R.drawable.ic_open_account),
                contentDescription = "open_account"
            )
            Spacer(modifier = Modifier.size(16.dp))
            RoundedCornerCheckbox(
                title = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            color = AppTheme.colorScheme.foregroundPrimaryDefault,
                        )
                    ){
                        append("قوانین افتتاح حساب بانک ملت ")
                    }
                    append("را مطالعه کردم و آن ها را می پذیرم.")
                },
                isChecked = checked
            ) {
                checked = !checked
            }
        }
    }
}