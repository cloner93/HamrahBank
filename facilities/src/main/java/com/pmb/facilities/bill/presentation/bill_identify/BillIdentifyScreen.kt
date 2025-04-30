package com.pmb.facilities.bill.presentation.bill_identify

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.facilities.R
import com.pmb.facilities.bill.domain.bill.entity.BillsType
import com.pmb.facilities.bill.presentation.BillSharedState
import com.pmb.facilities.bill.presentation.bill_identify.viewModel.BillIdentifyViewActions
import com.pmb.facilities.bill.presentation.bill_identify.viewModel.BillIdentifyViewModel
import com.pmb.facilities.bill.presentation.bill_identify.viewModel.BillIdentifyViewState
import com.pmb.facilities.component.PurchaseBillBottomSheet
import com.pmb.navigation.manager.LocalNavigationManager

@Composable
fun BillIdentifyScreen(
    sharedState: State<BillSharedState>,
    viewModel: BillIdentifyViewModel,
    updateState: (BillIdentifyViewState) -> Unit
) {
    val navigationManager = LocalNavigationManager.current
    var identifyNumber by remember {
        mutableStateOf("")
    }
    val viewState by viewModel.viewState.collectAsState()
    AppContent(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        topBar = {
            AppTopBar(
                title = sharedState.value.billType?.title ?: "",
                onBack = {
                    navigationManager.navigateBack()
                })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp, vertical = 17.dp
                    ),
                title = stringResource(com.pmb.ballon.R.string._continue),
                enable = true,
                onClick = {
                    viewModel.handle(BillIdentifyViewActions.GetBillData(identifyNumber))
                })
        },
    ) {
        AppNumberTextField(
            modifier = Modifier.fillMaxWidth(),
            value = identifyNumber,
            label = when (sharedState.value.billType?.type) {
                BillsType.TELECOMMUNICATION_BILL -> {
                    stringResource(R.string.phone_number_or_mobile)
                }

                else -> {
                    stringResource(R.string.identify_number)
                }
            },
            onValueChange = { identifyNumber = it })
    }
    if (viewState.bottomSheetVisibility) {
        viewState.billIdEntity?.let {
            PurchaseBillBottomSheet(
                it,
                onPurchaseClickListener = {
                    updateState.invoke(viewState)
                }
            ) {
                viewModel.handle(BillIdentifyViewActions.SetBottomSheetVisibility)
            }
        }
    }
}