package com.pmb.auth.presentaion.ekyc.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentaion.AuthScreens
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager

@Composable
fun AuthenticationScreen(navigationManager: NavigationManager) {
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.account_authentication),
                onBack = { }
            )

        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = true,
                title = stringResource(R.string.start_account_authentication),
                onClick = {
                    navigationManager.navigate(AuthScreens.FacePhotoCapture)
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        Image(
            modifier = Modifier.size(128.dp),
            painter = painterResource(com.pmb.ballon.R.drawable.ic_authentication),
            contentDescription = "authentication"
        )
        Spacer(modifier = Modifier.size(30.dp))
        BodyMediumText(
            text = stringResource(R.string.start_authentication_description),
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = buildAnnotatedString {
                append("\u2022")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(" شئونات اسلامی ")
                }
                append("را رعایت فرمایید.")
            },
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
            textAlign = TextAlign.Start,
            style = AppTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = buildAnnotatedString {
                append("\u2022")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(" ماسک و عینک ")
                }
                append("بر روی صورت نداشته باشید.")
            },
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
            textAlign = TextAlign.Start,
            style = AppTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = buildAnnotatedString {
                append("\u2022")
                append(" صورت خود را")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(" به طور کامل در کادر ")
                }
                append("مشخص شده قرار دهید.")
            },
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
            textAlign = TextAlign.Start,
            style = AppTheme.typography.bodyMedium
        )
    }
}