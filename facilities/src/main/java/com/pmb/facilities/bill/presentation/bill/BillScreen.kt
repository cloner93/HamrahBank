package com.pmb.facilities.bill.presentation.bill

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButtonWithIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.facilities.R
import com.pmb.facilities.R.drawable
import com.pmb.facilities.R.string
import com.pmb.facilities.bill.presentation.BillSharedState
import com.pmb.facilities.bill.presentation.bill.viewModel.BillViewActions
import com.pmb.facilities.bill.presentation.bill.viewModel.BillViewEvents
import com.pmb.facilities.bill.presentation.bill.viewModel.BillViewModel
import com.pmb.facilities.bill.presentation.bill.viewModel.BillViewState
import com.pmb.facilities.bill.presentation.bill_identify.viewModel.BillIdentifyViewActions
import com.pmb.facilities.complex_component.HistoryListComponent
import com.pmb.facilities.component.ChooseBillTypeBottomSheet
import com.pmb.facilities.component.PurchaseBillBottomSheet
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.BillScreens

@Composable
fun BillScreen(
    sharedState: State<BillSharedState>,
    viewModel: BillViewModel,
    updateState: (BillViewState) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    val viewState = viewModel.viewState.collectAsState()
    LaunchedEffect(Unit) {

        viewModel.viewEvent.collect { event ->
            when (event) {
                BillViewEvents.SetBillType -> {
                    updateState.invoke(viewState.value.copy())
                    navigationManager.navigate(BillScreens.BillIdentify)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBar = {
            AppTopBar(
                title = stringResource(string.bill_screen_title),
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
                title = stringResource(string.purchase_new_bill),
                icon = com.pmb.ballon.R.drawable.ic_add,
                enable = true,
                spacer = 5.dp
            ) {
                viewModel.handle(BillViewActions.SetBottomSheetVisibility)
            }
        },
        scrollState = null
    ) {
        viewState.value.billsData?.takeIf { !it.isNullOrEmpty() }?.let {
            HistoryListComponent(
                modifier = Modifier.fillMaxWidth(),
                pageImage = drawable.ic_contract,
                historyButtonTitle = stringResource(string.purchase_history),
                historyTitle = stringResource(string.bills),
                items = it,
                onHistoryClickListener = {
                    navigationManager.navigate(BillScreens.BillsHistory)
                }
            ) {
                viewModel.handle(BillViewActions.GetBillDataBasedId(it.subTitle))
            }
        }
    }
    if (viewState.value.isLoading) {
        AppLoading()
    }
    if (viewState.value.alertModelState != null) {
        AlertComponent(viewState.value.alertModelState!!)
    }
    if (viewState.value.isBottomSheetVisibility) {
        ChooseBillTypeBottomSheet(
            items = viewModel.getBillTypeData(),
            header = stringResource(R.string.choose_bill_type),
            onItemClickListener = {
                viewModel.handle(BillViewActions.SetBillTypeData(it))
                viewModel.handle(BillViewActions.SetBottomSheetVisibility)
            },
        ) {
            viewModel.handle(BillViewActions.SetBottomSheetVisibility)
        }

    }
    if (viewState.value.isPurchaseBottomSheetVisibility) {
        viewState.value.billIdEntity?.let {
            PurchaseBillBottomSheet(
                it,
                onPurchaseClickListener = {
                    updateState.invoke(viewState.value.copy())
                }
            ) {
                viewModel.handle(BillViewActions.SetPurchaseBottomSheetVisibility)
            }
        }
    }
}