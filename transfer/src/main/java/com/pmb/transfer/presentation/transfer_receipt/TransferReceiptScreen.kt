package com.pmb.transfer.presentation.transfer_receipt

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.node.Ref
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import androidx.core.graphics.createBitmap
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
import com.pmb.ballon.models.RowType
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.ReceiptStatus
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.presentation.components.ClientBankProfileInfo
import com.pmb.transfer.presentation.components.ReceiptStatusBadge
import com.pmb.transfer.presentation.transfer_receipt.viewmodel.TransferReceiptViewActions
import com.pmb.transfer.presentation.transfer_receipt.viewmodel.TransferReceiptViewEvents
import com.pmb.transfer.presentation.transfer_receipt.viewmodel.TransferReceiptViewModel
import com.pmb.transfer.presentation.transfer_receipt.viewmodel.TransferReceiptViewState
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@OptIn(ExperimentalComposeApi::class, ExperimentalComposeUiApi::class)
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
                            .clickable { shareReceipt = true },
                        spacer = 8.dp,
                        imageStyle = ImageStyle(size = Size.FIX(24.dp)),
                        image = com.pmb.ballon.R.drawable.ic_send,
                        text = stringResource(R.string.share),
                        textStyle = TextStyle(
                            color = AppTheme.colorScheme.onBackgroundTintNeutralDefault,
                            typography = AppTheme.typography.buttonSmall,
                            textAlign = TextAlign.Center
                        )
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    TextImage(
                        modifier = Modifier
                            .aspectRatio(156f / 76f)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .weight(1f)
                            .background(color = AppTheme.colorScheme.backgroundTintNeutralDefault)
                            .clickable { downloadReceipt = true },
                        spacer = 8.dp,
                        imageStyle = ImageStyle(size = Size.FIX(24.dp)),
                        image = com.pmb.ballon.R.drawable.ic_download,
                        text = stringResource(R.string.save_to_gallery),
                        textStyle = TextStyle(
                            color = AppTheme.colorScheme.onBackgroundTintNeutralDefault,
                            typography = AppTheme.typography.buttonSmall,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }


        },
        content = {
            ReceiptComponent(
                modifier = Modifier
                    .background(color = AppTheme.colorScheme.background1Neutral)
                    .padding(16.dp),
                rowTypes = viewState.rowTypes,
                headerContent = {
                    ReceiptHeaderComponent(viewState)
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
                    rowTypes = viewState.rowTypes,
                    captureMode = true,
                    headerContent = {
                        ReceiptHeaderComponent(viewState)
                    })
            }
        )
    }
}


@Composable
private fun ReceiptHeaderComponent(viewState: TransferReceiptViewState) {
    Spacer(modifier = Modifier.size(8.dp))
    viewState.receipt?.let { ClientBankProfileInfo(it.destination) }
    Spacer(modifier = Modifier.size(12.dp))
    ReceiptStatusBadge(viewState.receipt?.status ?: ReceiptStatus.UNKNOWN)
    viewState.receipt?.message?.let {
        Spacer(modifier = Modifier.size(12.dp))
        ButtonSmallText(text = it)
    }
}

@Composable
private fun ReceiptComponent(
    modifier: Modifier = Modifier,
    rowTypes: List<RowType>,
    captureMode: Boolean = false,
    headerContent: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        headerContent.invoke()
        ReceiptRowsComponent(rowTypes = rowTypes, captureMode = captureMode)
    }
}


@Composable
private fun ColumnScope.ReceiptRowsComponent(
    rowTypes: List<RowType>,
    captureMode: Boolean = false
) {
    Spacer(modifier = Modifier.size(24.dp))
    DashedDivider(color = AppTheme.colorScheme.strokeNeutral3Rest, thickness = 1.dp)
    val content: @Composable (RowType) -> Unit = {
        it.Render(modifier = Modifier.padding(vertical = 8.dp))
        DashedDivider(color = AppTheme.colorScheme.strokeNeutral3Rest, thickness = 1.dp)
    }

    if (captureMode) {
        rowTypes.forEach { content(it) }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(rowTypes.size) {
                content(
                    rowTypes[it]
                )
            }
        }
    }
    ReceiptFooterComponent()
}


@Composable
private fun ReceiptFooterComponent() {
    Spacer(modifier = Modifier.size(16.dp))
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

fun saveBitmapToGallery(
    context: Context,
    bitmap: Bitmap,
    displayName: String = "receipt_${System.currentTimeMillis()}"
): Boolean {
    val resolver = context.contentResolver

    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "$displayName.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }
    }

    val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        ?: return false

    resolver.openOutputStream(imageUri)?.use { outputStream: OutputStream ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        contentValues.clear()
        contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
        resolver.update(imageUri, contentValues, null, null)
    }

    return true
}


fun shareImage(context: Context, uri: Uri) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "image/png"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(Intent.createChooser(intent, "Share Image"))
}

fun getImageUri(context: Context, imageFile: File): Uri {
    return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", imageFile)
}

fun saveBitmapToCache(context: Context, bitmap: Bitmap): File {
    val cachePath = File(context.cacheDir, "images")
    cachePath.mkdirs() // Create directory if not exists
    val file = File(cachePath, "shared_image.png")

    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    return file
}


@Composable
fun ComposeToBitmap(
    modifier: Modifier = Modifier,
    onBitmapReady: (Bitmap) -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val viewRef = remember { Ref<ComposeView>() }

    AndroidView(
        modifier = modifier.size(1.dp), // خیلی کوچیک برای اینکه off-screen بمونه
        factory = {
            ComposeView(context).apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
                setContent {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        content.invoke()
                    }
                }
                viewRef.value = this
            }
        },
        update = { view ->
            view.post {
                view.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                )
                view.layout(0, 0, view.measuredWidth, view.measuredHeight)

                if (view.measuredWidth > 0 && view.measuredHeight > 0) {
                    val bitmap = createBitmap(
                        view.measuredWidth,
                        view.measuredHeight
                    ).copy(Bitmap.Config.ARGB_8888, true) // ⛑ مهم برای جلوگیری از hardware bitmap
                    try {
                        val canvas = Canvas(bitmap)
                        view.draw(canvas)
                        onBitmapReady(bitmap)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    )
}


