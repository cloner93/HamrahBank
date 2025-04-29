package com.pmb.facilities.charge.presentation.choose_charge_price

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.facilities.R
import com.pmb.facilities.charge.presentation.ChargeSharedState
import com.pmb.facilities.charge.presentation.choose_charge_price.viewModel.ChooseChargePriceViewActions
import com.pmb.facilities.charge.presentation.choose_charge_price.viewModel.ChooseChargePriceViewModel
import com.pmb.facilities.charge.presentation.choose_charge_price.viewModel.ChooseChargePriceViewState
import com.pmb.facilities.complex_component.ChoosePriceComponent
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.ChargeScreens

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChooseChargePriceScreen(
    sharedState: State<ChargeSharedState>,
    viewModel: ChooseChargePriceViewModel,
    updateState: (ChooseChargePriceViewState) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    AppContent(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.choose_charge_price),
                onBack = {
                    navigationManager.navigateBack()
                })
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
                    updateState.invoke(viewState.copy())
                    navigationManager.navigate(ChargeScreens.ChargeConfirm)
                })
        }
    ) {
        viewState.chargePriceData?.let {
            ChoosePriceComponent(
                headerImage = sharedState.value.operator?.operatorImage ?: 0,
                headerText = sharedState.value.simNumber,
                chosenText = stringResource(R.string.choose_your_charge),
                items = it
            ) {
                viewModel.handle(ChooseChargePriceViewActions.SetSelectedPrice(it))
            }
        }

    }
}
