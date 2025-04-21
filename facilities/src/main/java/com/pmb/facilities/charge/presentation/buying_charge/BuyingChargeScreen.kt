package com.pmb.facilities.charge.presentation.buying_charge

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.facilities.R
import com.pmb.facilities.complex_component.ChoosePhoneNumberComponent

@Composable
fun BuyingChargeScreen() {
    val item = listOf<ChooseOperator>(
        ChooseOperator(
            id = 0,
            operator = "همراه اول",
            operatorImage = R.drawable.ic_mci,
        ),
        ChooseOperator(
            id = 2,
            operator = "ایرانسل",
            operatorImage = R.drawable.ic_irancell,
            isChecked = true
        ),
        ChooseOperator(
            id = 3,
            operator = "رایتل",
            operatorImage = R.drawable.ic_rightel,
        ),
    )
    var mobile by remember { mutableStateOf("") }
    var isMobile by remember { mutableStateOf(false) }
    AppContent(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.buy_charge),
                onBack = { })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp,
                        vertical = 17.dp
                    ),
                title = stringResource(com.pmb.ballon.R.string._continue),
                enable = true,
                onClick = {

                })
        }) {
        ChoosePhoneNumberComponent(
            modifier = Modifier.fillMaxWidth(),
            mobile = mobile,
            item = item,
            onValidate = {
                isMobile = it
            }
        ) {
            mobile = it
        }
    }
}

data class ChooseOperator(
    val id: Int,
    val operator: String,
    val operatorImage: Int,
    val isChecked: Boolean = false
)

@Preview
@Composable
fun ChargeScreenPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        BuyingChargeScreen()
    }
}