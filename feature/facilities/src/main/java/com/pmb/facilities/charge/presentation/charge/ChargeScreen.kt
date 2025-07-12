package com.pmb.facilities.charge.presentation.charge

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButtonWithIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.facilities.R.drawable
import com.pmb.facilities.R.string
import com.pmb.facilities.charge.presentation.ChargeSharedState
import com.pmb.facilities.complex_component.HistoryListComponent
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.ChargeScreens

@Composable
fun ChargeScreen(
    viewModel: ChargeViewModel,
    sharedState: State<ChargeSharedState>,
    updateState: (ChargeViewState) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState = viewModel.viewState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {

        viewModel.viewEvent.collect { event ->
            when (event) {
                ChargeViewEvents.UseTheLatestNumber -> {
                    updateState.invoke(viewState.value.copy())
                    navigationManager.navigate(ChargeScreens.ChooseChargePrice)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBar = {
            AppTopBar(
                title = stringResource(string.charge_screen_title),
                onBack = {
                    navigationManager.navigateBack()
                })
        },
        footer = {
            AppButtonWithIcon(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
                title = stringResource(string.buy_new_charge),
                icon = com.pmb.ballon.R.drawable.ic_add,
                enable = true,
                spacer = 5.dp
            ) {
                navigationManager.navigate(ChargeScreens.PurchaseCharge)
            }
        },
        scrollState = null
    ) {
        viewState.value.simCardList?.takeIf { !it.isNullOrEmpty() }?.let {
            HistoryListComponent(
                modifier = Modifier.fillMaxWidth(),
                pageImage = drawable.ic_charge,
                historyButtonTitle  = stringResource(string.buying_history),
                historyTitle= stringResource(string.latest_number),
                items = it,
                onHistoryClickListener = {
                    navigationManager.navigate(ChargeScreens.ChargeHistory)
                }
            ) {
                viewModel.handle(ChargeViewActions.SetSelectedSimNumber(it))
            }
        }
    }
    if (viewState.value.isLoading) {
        AppLoading()
    }
    if (viewState.value.alertModelState != null) {
        AlertComponent(viewState.value.alertModelState!!)
    }
}

