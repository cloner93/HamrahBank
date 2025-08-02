package com.pmb.transfer.presentation.transfer_method

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.ItemVerticalTextIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.PaymentType
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.presentation.transfer_method.viewmodel.TransferMethodViewActions
import com.pmb.transfer.presentation.transfer_method.viewmodel.TransferMethodViewEvents
import com.pmb.transfer.presentation.transfer_method.viewmodel.TransferMethodViewModel

@Composable
fun TransferMethodScreen(
    viewModel: TransferMethodViewModel,
    methods: List<TransferMethodEntity>,
    selectedTransferMethod: (TransferMethodEntity) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current
    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferMethodViewEvents.NavigateToTransferConfirm -> {
                    selectedTransferMethod.invoke(event.item)
                    navigationManager.navigate(TransferScreens.TransferConfirm)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.handle(TransferMethodViewActions.SetPaymentTypes(methods))
    }

    Box(modifier = Modifier.background(color = AppTheme.colorScheme.background1Neutral)) {
        AppContent(
            scrollState = null,
            topBar = {
                AppTopBar(
                    title = stringResource(R.string.transfer_method),
                    onBack = { navigationManager.navigateBack() })
            }
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)) {

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(viewState.transferMethods.size) { index ->
                        val item = viewState.transferMethods[index]
                        ItemVerticalTextIcon(
                            title = item.title,
                            subtitle = item.detail + if (item.fee > 0.0) " | کارمزد: ${item.fee.toCurrency()} ریال " else "",
                            enable = item.active,
                            startIcon =
                                when (item.paymentType) {
                                    PaymentType.CARD_TO_CARD -> com.pmb.ballon.R.drawable.ic_bank_card_swap
                                    PaymentType.MELLAT_TO_MELLAT -> com.pmb.ballon.R.drawable.ic_mellat_to_mellat
                                    else -> com.pmb.ballon.R.drawable.ic_university
                                },
                            onClick = {
                                viewModel.handle(TransferMethodViewActions.SelectPaymentType(item))
                            })
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }

    "انتقال در لحظه | کارمزد: ۵٬۰۰۰ ریال"

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}