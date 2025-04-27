package com.pmb.facilities.charge.presentation.buying_charge

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.facilities.R
import com.pmb.facilities.charge.presentation.ChargeSharedState
import com.pmb.facilities.complex_component.ChoosePhoneNumberComponent

@Composable
fun BuyingChargeScreen(
    sharedState: State<ChargeSharedState>, viewModel: BuyingChargeViewModel,
    updateState: (BuyingChargeViewState) -> Unit
) {

    LaunchedEffect(Unit) {
        Log.d("Masoud Tag", "BuyingChargeScreen: ${sharedState.value.simNumber}")

    }
    AppContent(modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBar = {
            AppTopBar(title = stringResource(R.string.buy_charge), onBack = { })
        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp, vertical = 17.dp
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

//@Preview
//@Composable
//fun ChargeScreenPreview() {
//    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
//        BuyingChargeScreen()
//    }
//}