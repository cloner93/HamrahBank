package com.pmb.facilities.charge.presentation.charge

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppButtonWithIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.facilities.R
import com.pmb.facilities.complex_component.HistoryListComponent

@Composable
fun ChargeScreen() {
    val items = listOf<ChargeData>(
        ChargeData(
            id = 0,
            imageString = com.pmb.facilities.R.drawable.ic_irancell,
            operator = "ایرانسل",
            phoneNumber = "۰۹۹۱۱۰۵۱۷۲۵"
        ),
        ChargeData(
            id = 1,
            imageString = com.pmb.facilities.R.drawable.ic_irancell,
            operator = "ایرانسل",
            phoneNumber = "۰۹۹۲۴۹۲۰۷۹۰"
        ),
        ChargeData(
            id = 2,
            imageString = com.pmb.facilities.R.drawable.ic_irancell,
            operator = "ایرانسل",
            phoneNumber = "09308160417"
        ),
    )
    AppContent(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.charge_screen_title),
                onBack = { })
        },
        footer = {
            AppButtonWithIcon(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                title = stringResource(R.string.buy_new_charge),
                icon = com.pmb.ballon.R.drawable.ic_add,
                enable = true,
                spacer = 5.dp
            ) {

            }
        },
        scrollState = null
    ) {
        HistoryListComponent(
            modifier = Modifier.fillMaxWidth(),
            pageImage = R.drawable.ic_charge,
            historyTitle = stringResource(R.string.buying_history),
            historyButtonTitle = stringResource(R.string.latest_number),
            items = items
        )
    }
}

@Preview
@Composable
fun ChargeScreenPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ChargeScreen()
    }
}

data class ChargeData(
    val id: Int,
    val imageString: Int,
    val operator: String,
    val phoneNumber: String
)