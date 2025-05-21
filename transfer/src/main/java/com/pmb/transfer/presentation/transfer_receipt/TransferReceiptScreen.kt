package com.pmb.transfer.presentation.transfer_receipt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.utils.ComposeToBitmap
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.getImageUri
import com.pmb.core.utils.saveBitmapToCache
import com.pmb.core.utils.saveBitmapToGallery
import com.pmb.core.utils.shareImage
import com.pmb.core.utils.shareText
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.receipt.component.ReceiptComponent
import com.pmb.receipt.component.ReceiptSaveOrShareComponent
import com.pmb.receipt.model.mapToRowType
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.presentation.components.ReceiptHeaderComponent
import com.pmb.transfer.presentation.transfer_receipt.viewmodel.TransferReceiptViewActions
import com.pmb.transfer.presentation.transfer_receipt.viewmodel.TransferReceiptViewEvents
import com.pmb.transfer.presentation.transfer_receipt.viewmodel.TransferReceiptViewModel

@Composable
fun TransferReceiptScreen(
    viewModel: TransferReceiptViewModel,
    transferReceipt: TransferReceiptEntity?,
    clearData: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current
    var shareReceipt by remember { mutableStateOf(false) }
    var downloadReceipt by remember { mutableStateOf(false) }
    val navigationManager = LocalNavigationManager.current
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                TransferReceiptViewEvents.SaveReceiptToGallery -> Unit
                TransferReceiptViewEvents.ShareReceipt -> Unit
            }
        }
    }

    LaunchedEffect(transferReceipt) {
        viewModel.handle(TransferReceiptViewActions.UpdateTransferReceipt(transferReceipt))
    }

    AppContent(
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        scrollState = null,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.mony_transfer_receipt),
                onBack = {
                    clearData.invoke()
                    navigationManager.navigateAndClearStack(TransferScreens.TransferGraph)
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        footer = {
            ReceiptSaveOrShareComponent(
                shareReceipt = {
                    viewModel.handle(TransferReceiptViewActions.ShareReceiptClicked)
                },
                downloadReceipt = { downloadReceipt = true }
            )
        },
        content = {
            ReceiptComponent(
                modifier = Modifier
                    .background(color = AppTheme.colorScheme.background1Neutral)
                    .padding(16.dp),
                rowTypes = viewState.rows.map { it.mapToRowType() },
                headerContent = {
                    ReceiptHeaderComponent(viewState.receipt)
                })
        })

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }


    if (shareReceipt || downloadReceipt) {
        ComposeToBitmap(
            modifier = Modifier,
            onBitmapReady = { bitmap ->
                if (shareReceipt)
                    shareImage(context, getImageUri(context, saveBitmapToCache(context, bitmap)))
                else if (downloadReceipt) saveBitmapToGallery(context, bitmap)
                shareReceipt = false
                downloadReceipt = false
            },
            content = {
                val configuration = LocalConfiguration.current
                val screenWidthDp = configuration.screenWidthDp.dp

                ReceiptComponent(
                    modifier = Modifier
                        .width(screenWidthDp)
                        .background(color = AppTheme.colorScheme.background1Neutral)
                        .padding(16.dp),
                    rowTypes = viewState.rows.map { it.mapToRowType() },
                    captureMode = true,
                    headerContent = {
                        ReceiptHeaderComponent(viewState.receipt)
                    })
            }
        )
    }

    if (viewState.showShareBottomSheet) {
        MenuBottomSheet(
            title = stringResource(com.pmb.ballon.R.string.select_share_type),
            items = listOf(
                MenuSheetModel(
                    title = stringResource(com.pmb.ballon.R.string.text),
                    icon = com.pmb.ballon.R.drawable.ic_text,
                    iconTint = { AppTheme.colorScheme.onBackgroundNeutralCTA },
                    showEndIcon = false,
                    onClicked = {
                        context.shareText("textToShare")// TODO:: get receipt text
                    }
                ),
                MenuSheetModel(
                    title = stringResource(com.pmb.ballon.R.string.image),
                    icon = com.pmb.ballon.R.drawable.ic_image,
                    iconTint = { AppTheme.colorScheme.onBackgroundNeutralCTA },
                    showEndIcon = false,
                    onClicked = {
                        shareReceipt = true
                    }
                )

            ),
            onDismiss = {
                viewModel.handle(TransferReceiptViewActions.OnDismissShareReceiptBottomSheet)
            }
        )
    }
}