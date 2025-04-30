package com.pmb.facilities.bill.presentation.bill_identify

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
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.core.utils.isMobile
import com.pmb.facilities.R
import com.pmb.facilities.bill.domain.bill.entity.BillsType
import com.pmb.facilities.bill.presentation.BillSharedState
import com.pmb.facilities.bill.presentation.bill_identify.viewModel.BillIdentifyViewActions
import com.pmb.facilities.bill.presentation.bill_identify.viewModel.BillIdentifyViewModel
import com.pmb.facilities.bill.presentation.bill_identify.viewModel.BillIdentifyViewState
import com.pmb.facilities.component.PurchaseBillBottomSheet
import com.pmb.facilities.component.TeleComBillBottomSheet
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
    var isMobile by remember {
        mutableStateOf(false)
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
                enable = isMobile,
                onClick = {
                    when (sharedState.value.billType?.type) {
                        BillsType.TELECOMMUNICATION_BILL -> {
                            viewModel.handle(
                                BillIdentifyViewActions.GetTeleComBillDataBasedId(
                                    identifyNumber
                                )
                            )
                        }

                        else -> {
                            viewModel.handle(BillIdentifyViewActions.GetBillData(identifyNumber))
                        }
                    }
                })
        },
    ) {
        when (sharedState.value.billType?.type) {
            BillsType.TELECOMMUNICATION_BILL -> {
                AppMobileTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = identifyNumber,
                    label =
                        stringResource(R.string.phone_number_or_mobile),
                    onValidate = {
                        isMobile = it
                    },
                    onValueChange = { identifyNumber = it }
                )
            }

            else -> {
                AppNumberTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = identifyNumber,
                    label = stringResource(R.string.identify_number),
                    onValueChange = {
                        val result = it.isMobile()
                        isMobile = !result.isValid
                        identifyNumber = it
                    })
            }
        }
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
    if (viewState.isTeleComBottomSheetVisibility) {
        viewState.teleCommunicationEntity?.let {
            TeleComBillBottomSheet(
                it,
                onPurchaseClickListener = {
                    updateState.invoke(viewState.copy())
                }) {
                viewModel.handle(BillIdentifyViewActions.SetTeleComBottomSheetVisibility)
            }
        }
    }
}