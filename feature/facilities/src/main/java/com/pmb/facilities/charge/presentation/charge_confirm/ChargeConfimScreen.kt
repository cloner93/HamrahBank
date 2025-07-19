package com.pmb.facilities.charge.presentation.charge_confirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.facilities.R
import com.pmb.facilities.component.PriceWithTaxColumnComponent
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.ChargeScreens

@Composable
fun ChargeConfirmScreen() {
    var favoriteChecked by remember {
        mutableStateOf(false)
    }
    val navigationManager = LocalNavigationManager.current
    AppContent(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.charge_confirm),
                onBack = {

                })
        }, footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 17.dp
                    ),
                title = stringResource(com.pmb.ballon.R.string.confirm),
                enable = true,
                onClick = {
                    navigationManager.navigate(ChargeScreens.ChargeReceipt)
                })
        }
    ) {
        TextImage(
            image = R.drawable.ic_irancell,
            imageStyle = ImageStyle(size = Size.FIX(48.dp)),
            text = "09308160417",
            spacer = 0.dp,
            textStyle = TextStyle(
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                typography = AppTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.size(12.dp))
        PriceWithTaxColumnComponent(
            price = "100000",
            tax = "شارژ + مالیات"
        )
        Spacer(Modifier.size(40.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = AppTheme.colorScheme.strokeNeutral3Divider
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyMediumText(
                text = stringResource(R.string.save_to_favorite_destination),
                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
            )
            Switch(checked = favoriteChecked, onCheckedChange = {
                favoriteChecked = !favoriteChecked
            })
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = AppTheme.colorScheme.strokeNeutral3Divider
        )
    }
}

@Preview
@Composable
fun ChooseChargePriceScreenPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ChargeConfirmScreen()
    }
}