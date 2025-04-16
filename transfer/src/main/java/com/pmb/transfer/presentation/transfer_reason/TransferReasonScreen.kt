package com.pmb.transfer.presentation.transfer_reason

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
import com.pmb.ballon.component.ItemCheckRow
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.presentation.transfer_reason.viewmodel.TransferReasonViewActions
import com.pmb.transfer.presentation.transfer_reason.viewmodel.TransferReasonViewEvents
import com.pmb.transfer.presentation.transfer_reason.viewmodel.TransferReasonViewModel

@Composable
fun TransferReasonScreen(
    navigationManager: NavigationManager,
    viewModel: TransferReasonViewModel,
    selectedReason: (ReasonEntity?) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is TransferReasonViewEvents.NavigateUp -> {
                    selectedReason.invoke(event.item)
                    navigationManager.navigateBack()
                }
            }
        }
    }


    AppContent(
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.transfer_method),
                onBack = {
                    selectedReason.invoke(viewState.selectedReason)
                    navigationManager.navigateBack()
                })
        }
    ) {
        Column(modifier = Modifier.padding(top = 16.dp)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewState.reasons.size) { index ->
                    val item = viewState.reasons[index]
                    ItemCheckRow(
                        title = item.title,
                        checked = item.id == viewState.selectedReason?.id,
                        onCheckedChange = {
                            viewModel.handle(TransferReasonViewActions.SelectReason(item))
                        }
                    )
                }
            }
        }
    }

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}