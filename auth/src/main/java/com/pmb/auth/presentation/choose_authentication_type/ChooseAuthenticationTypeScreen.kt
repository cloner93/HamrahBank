package com.pmb.auth.presentation.choose_authentication_type

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.utils.ComingType
import com.pmb.ballon.component.base.AppButtonWithWeightIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.Headline4Text
import com.pmb.ballon.models.AppButton.buttonColors
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager

@Composable
fun ChooseAuthenticationTypeScreen(navigationManager: NavigationManager, comingType: ComingType) {
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.authentication),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            Spacer(modifier = Modifier.size(100.dp))
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        AppImage(
            image = if (comingType == ComingType.COMING_PASSWORD) painterResource(com.pmb.ballon.R.drawable.img_key) else painterResource(
                com.pmb.ballon.R.drawable.img_document
            ),
            style = ImageStyle(size = Size.FIX(all = 128.dp))
        )
        Spacer(modifier = Modifier.size(16.dp))
        Headline4Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            text = if (comingType == ComingType.COMING_PASSWORD) stringResource(R.string.authentication_type_password_description) else stringResource(
                R.string.authentication_type_activation_description
            ),
            color = AppTheme.colorScheme.onBackgroundPrimaryActive
        )
        Spacer(modifier = Modifier.weight(1f))
        AppButtonWithWeightIcon(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            title = stringResource(R.string.bank_card),
            icon = com.pmb.ballon.R.drawable.ic_arrow_left,
            colors = buttonColors(
                containerColor = Color.White
            ),
            textStyle = TextStyle.defaultButton(
                color = AppTheme.colorScheme.onBackgroundPrimaryActive,
                typography = AppTheme.typography.bodyMedium
            ),
        ) {

        }
        AppButtonWithWeightIcon(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            title = stringResource(R.string.visual_authentication),
            icon = com.pmb.ballon.R.drawable.ic_arrow_left,
            colors = buttonColors(
                containerColor = Color.White
            ),
            textStyle = TextStyle.defaultButton(
                color = AppTheme.colorScheme.onBackgroundPrimaryActive,
                typography = AppTheme.typography.bodyMedium
            ),
        ) {

        }
        AppButtonWithWeightIcon(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            title = stringResource(R.string.mellat_signature_app),
            icon = com.pmb.ballon.R.drawable.ic_arrow_left,
            colors = buttonColors(
                containerColor = Color.White
            ),
            textStyle = TextStyle.defaultButton(
                color = AppTheme.colorScheme.onBackgroundPrimaryActive,
                typography = AppTheme.typography.bodyMedium
            ),
        ) {

        }
    }
}