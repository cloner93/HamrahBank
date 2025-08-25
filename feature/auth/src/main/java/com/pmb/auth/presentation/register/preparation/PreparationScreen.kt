package com.pmb.auth.presentation.register.preparation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.RegisterScreens

@Composable
fun PreparationScreen() {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.account_opening),
                onBack = {
                    navigationManager.navigateBack()
                }
            )

        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = true,
                title = stringResource(R.string.ready),
                onClick = {
                    navigationManager.navigate(RegisterScreens.Register)
                })
        }
    ) {
        Spacer(
            modifier = Modifier.size(24.dp)
        )
        BodyMediumText(
            text = stringResource(R.string.opening_account_description),
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier.size(32.dp)
        )
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                AppImage(image = com.pmb.ballon.R.drawable.ic_sim)
                Spacer(modifier = Modifier.width(8.dp))
                BodyMediumText(
                    modifier = Modifier,
                    text = stringResource(R.string.sim_description),
                    color = AppTheme.colorScheme.foregroundNeutralDefault
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                AppImage(image = com.pmb.ballon.R.drawable.ic_national_id_desc)
                Spacer(modifier = Modifier.width(8.dp))
                BodyMediumText(
                    modifier = Modifier,
                    text = stringResource(R.string.national_id_description),
                    color = AppTheme.colorScheme.foregroundNeutralDefault
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                AppImage(image = com.pmb.ballon.R.drawable.ic_signature)
                Spacer(modifier = Modifier.width(8.dp))
                BodyMediumText(
                    modifier = Modifier,
                    text = stringResource(R.string.signature_description),
                    color = AppTheme.colorScheme.foregroundNeutralDefault
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                AppImage(image = com.pmb.ballon.R.drawable.ic_camera)
                Spacer(modifier = Modifier.width(8.dp))
                BodyMediumText(
                    modifier = Modifier,
                    text = stringResource(R.string.authentication_coverage_description),
                    color = AppTheme.colorScheme.foregroundNeutralDefault
                )
            }
        }
    }
}