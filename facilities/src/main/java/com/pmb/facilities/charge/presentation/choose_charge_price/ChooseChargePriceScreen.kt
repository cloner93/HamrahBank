package com.pmb.facilities.charge.presentation.choose_charge_price

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.facilities.R
import com.pmb.facilities.complex_component.ChoosePriceComponent

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChooseChargePriceScreen() {
    val items = listOf<String>(
        "500000",
        "1000000",
        "2000000",
        "5000000",
        "10000000",
    )

    AppContent(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.choose_charge_price),
                onBack = { })
        }, footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 17.dp
                    ),
                title = stringResource(com.pmb.ballon.R.string._continue),
                enable = true,
                onClick = {

                })
        }
    ) {
        ChoosePriceComponent(
            headerImage = R.drawable.ic_irancell,
            headerText = "09308160417",
            chosenText = stringResource(R.string.choose_your_charge),
            items = items
        )

    }
}

@Preview
@Composable
fun ChooseChargePriceScreenPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ChooseChargePriceScreen()
    }
}