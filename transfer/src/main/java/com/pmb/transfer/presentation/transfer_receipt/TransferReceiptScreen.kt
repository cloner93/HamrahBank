package com.pmb.transfer.presentation.transfer_receipt

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Picture
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.DashedDivider
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ButtonSmallText
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Render
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.ReceiptStatus
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.presentation.components.ClientBankProfileInfo
import com.pmb.transfer.presentation.components.ReceiptStatusBadge
import com.pmb.transfer.presentation.transfer_receipt.viewmodel.TransferReceiptViewActions
import com.pmb.transfer.presentation.transfer_receipt.viewmodel.TransferReceiptViewEvents
import com.pmb.transfer.presentation.transfer_receipt.viewmodel.TransferReceiptViewModel
import com.pmb.transfer.presentation.transfer_receipt.viewmodel.TransferReceiptViewState

@Composable
fun TransferReceiptScreen(
    navigationManager: NavigationManager,
    viewModel: TransferReceiptViewModel,
    transferReceipt: TransferReceiptEntity?
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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
        modifier = Modifier.padding(all = 16.dp),
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.mony_transfer_receipt),
                onBack = { navigationManager.navigateBack() })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        footer = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextImage(
                        modifier = Modifier
                            .aspectRatio(156f / 76f)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .weight(1f)
                            .background(color = AppTheme.colorScheme.backgroundTintNeutralDefault)
                            .clickable { },
                        spacer = 8.dp,
                        imageStyle = ImageStyle(size = Size.FIX(24.dp)),
                        image = com.pmb.ballon.R.drawable.ic_share,
                        text = stringResource(R.string.share),
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    TextImage(
                        modifier = Modifier
                            .aspectRatio(156f / 76f)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .weight(1f)
                            .background(color = AppTheme.colorScheme.backgroundTintNeutralDefault)
                            .clickable {

                            },
                        spacer = 8.dp,
                        imageStyle = ImageStyle(size = Size.FIX(24.dp)),
                        image = com.pmb.ballon.R.drawable.ic_download,
                        text = stringResource(R.string.save_to_gallery)
                    )
                }
            }


        },
        content = {
            ReceiptComponent(viewState)
        })

    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}


@Composable
fun ReceiptComponent(viewState: TransferReceiptViewState) {
    Column(
        modifier = Modifier.padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        viewState.receipt?.let { ClientBankProfileInfo(it.account) }
        Spacer(modifier = Modifier.size(12.dp))
        ReceiptStatusBadge(viewState.receipt?.status ?: ReceiptStatus.UNKNOWN)
        viewState.receipt?.message?.let {
            Spacer(modifier = Modifier.size(12.dp))
            ButtonSmallText(text = it)
        }
        Spacer(modifier = Modifier.size(24.dp))
        DashedDivider(color = AppTheme.colorScheme.strokeNeutral3Rest, thickness = 1.dp)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewState.rowTypes.size) { index ->
                viewState.rowTypes[index].Render(modifier = Modifier.padding(vertical = 8.dp))
                DashedDivider(color = AppTheme.colorScheme.strokeNeutral3Rest, thickness = 1.dp)
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            AppImage(
                image = painterResource(com.pmb.ballon.R.drawable.img_mellat_logo),
                style = ImageStyle(size = Size.Rectangle(width = 36.dp, height = 42.dp))
            )
            Spacer(modifier = Modifier.size(8.dp))
            AppImage(image = painterResource(com.pmb.ballon.R.drawable.ic_mellat_bank))
        }
        Spacer(modifier = Modifier.size(22.dp))
    }
}


fun saveBitmapToGallery(context: Context, bitmap: Bitmap): Uri? {
    val filename = "screenshot_${System.currentTimeMillis()}.png"
    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
    }

    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    uri?.let {
        resolver.openOutputStream(it)?.use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
    }

    return uri
}

fun shareBitmap(context: Context, uri: Uri) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "image/png"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(Intent.createChooser(shareIntent, "Share Image"))
}


