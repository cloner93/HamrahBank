package com.pmb.account.presentation.transactionReceipt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.account.R
import com.pmb.account.presentation.transactionReceipt.viewmodel.TransactionReceiptViewModel
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.Headline5Text
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.utils.ComposeToBitmap
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.core.utils.getImageUri
import com.pmb.core.utils.saveBitmapToCache
import com.pmb.core.utils.saveBitmapToGallery
import com.pmb.core.utils.shareImage
import com.pmb.core.utils.shareText
import com.pmb.domain.model.TransactionType
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.receipt.component.ReceiptComponent
import com.pmb.receipt.component.ReceiptSaveOrShareComponent
import com.pmb.receipt.model.mapToRowType

@Composable
fun TransactionsReceiptScreen() {
    val viewModel = hiltViewModel<TransactionReceiptViewModel>()
    val viewState by viewModel.viewState.collectAsState()

    val navigationManager = LocalNavigationManager.current

    var showShareBottomSheet by remember { mutableStateOf(false) }
    var shareReceipt by remember { mutableStateOf(false) }
    var downloadReceipt by remember { mutableStateOf(false) }

    val context = LocalContext.current

    AppContent(
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        scrollState = null,
        horizontalAlignment = Alignment.CenterHorizontally,
        topBar = {
            AppTopBar(
                title = "جزئیات تراکنش",
                startIcon = ClickableIcon(
                    icon = IconType.ImageVector(Icons.Default.Close),
                    onClick = {
                        navigationManager.navigateBack()
                    })
            )
        },
        footer = {
            ReceiptSaveOrShareComponent(
                shareReceipt = {
                    showShareBottomSheet = true
                },
                downloadReceipt = { downloadReceipt = true }
            )
        }
    ) {
        ReceiptComponent(
            modifier = Modifier
                .background(color = AppTheme.colorScheme.background1Neutral)
                .padding(16.dp),
            rowTypes = viewState.rows.map { it.mapToRowType() },
            headerContent = {
                TransactionReceiptHeader(
                    image = when (viewState.transaction?.type) {
                        TransactionType.DEPOSIT -> R.drawable.ic_transfer
                        TransactionType.WITHDRAWAL -> R.drawable.ic_transfer
                        TransactionType.TRANSFER -> R.drawable.ic_transfer
                        TransactionType.RECEIVE -> R.drawable.ic_receive
                        TransactionType.FEE -> R.drawable.ic_transfer
                        else -> R.drawable.ic_transfer
                    },
                    headerTitle = viewState.transaction?.title ?: "",
                    headerSubTitle = ""
                )
            },
            footerContent = { }

        )
    }

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
                    headerContent = { })
            }
        )
    }

    if (showShareBottomSheet) {
        MenuBottomSheet(
            title = stringResource(com.pmb.ballon.R.string.select_share_type),
            items = listOf(
                MenuSheetModel(
                    title = stringResource(com.pmb.ballon.R.string.text),
                    icon = com.pmb.ballon.R.drawable.ic_text,
                    iconTint = { AppTheme.colorScheme.onBackgroundNeutralCTA },
                    showEndIcon = false,
                    onClicked = {
                        context.shareText(viewState.sharedText)
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
                showShareBottomSheet = false
            }
        )
    }

}

@Composable
fun TransactionReceiptHeader(image: Int, headerTitle: String, headerSubTitle: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        AppImage(
            image = painterResource(image),
            style = ImageStyle(Size.FIX(48.dp))
        )
        Spacer(modifier = Modifier.height(12.dp))
        Headline5Text(
            text = headerTitle,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.foregroundPrimaryDefault
        )
        BodyMediumText(
            text = headerSubTitle,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
    }
}

@AppPreview
@Composable
private fun TransactionsReceiptScreenPreview() {
    HamrahBankTheme {
        TransactionsReceiptScreen()
    }
}