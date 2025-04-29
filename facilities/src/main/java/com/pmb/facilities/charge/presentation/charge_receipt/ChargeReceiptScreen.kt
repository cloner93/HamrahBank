package com.pmb.facilities.charge.presentation.charge_receipt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.MenuBottomSheet
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.receipt.ReceiptComponent
import com.pmb.ballon.component.receipt.ReceiptSaveOrShareComponent
import com.pmb.ballon.models.MenuSheetModel
import com.pmb.ballon.models.RowType
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.AppTypography
import com.pmb.ballon.ui.theme.lightColors
import com.pmb.core.utils.Convert
import com.pmb.core.utils.shareText
import com.pmb.core.utils.toCurrency
import com.pmb.facilities.R
import com.pmb.facilities.component.ChargeReceiptHeader
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ChargeReceiptScreen() {
    val context = LocalContext.current
    val rowTypes = listOf(
        RowType.PaymentRow(
            title = "مبلغ",
            amount = 10000000.0.toCurrency(),
            currency = "ریال",
            textStyle = titleStyle(),
            amountStyle = amountStyle(),
            currencyStyle = currencyStyle(),
        ),
        RowType.TwoTextRow(
            title = "زمان",
            subtitle = Convert.timestampToPersianDate(1744455441),
            textStyle = titleStyle(),
            subtitleStyle = subtitleStyle(),
        ),
        RowType.TwoTextRow(
            title = "کارت مبدأ",
            subtitle =
                "۶۰۳۷۶۹۱۹۰۱۲۳۴۵۶۷",
            textStyle = titleStyle(),
            subtitleStyle = subtitleStyle(),
            subtitleLayoutDirection = LayoutDirection.Ltr
        ),
        RowType.TwoTextRow(
            title = "شماره پیگیری",
            subtitle = "13710828",
            textStyle = titleStyle(),
            subtitleStyle = subtitleStyle(),
        )
    )
    var shareReceipt by remember { mutableStateOf(false) }
    var downloadReceipt by remember { mutableStateOf(false) }
    var showShareBottomSheet by remember { mutableStateOf(false) }
    AppContent(
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        scrollState = null,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.charge_receipt),
                startIcon = ClickableIcon(
                    icon = IconType.ImageVector(Icons.Default.Close),
                    onClick = {

                    })
            )
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        footer = {
            ReceiptSaveOrShareComponent(
                shareReceipt = {
                    showShareBottomSheet = true
                },
                downloadReceipt = { downloadReceipt = true }
            )
        },
        content = {
            ReceiptComponent(
                modifier = Modifier
                    .background(color = AppTheme.colorScheme.background1Neutral)
                    .padding(16.dp),
                rowTypes = rowTypes,
                headerContent = {
                    ChargeReceiptHeader(
                        image = R.drawable.ic_irancell,
                        headerTitle = "شارژ",
                        headerSubTitle = "09308160417"
                    )
                })
        })
//    if (shareReceipt || downloadReceipt) {
//        ComposeToBitmap(
//            modifier = Modifier,
//            onBitmapReady = { bitmap ->
//                if (shareReceipt)
//                    shareImage(context, getImageUri(context, saveBitmapToCache(context, bitmap)))
//                else if (downloadReceipt) saveBitmapToGallery(context, bitmap)
//                shareReceipt = false
//                downloadReceipt = false
//            },
//            content = {
//                val configuration = LocalConfiguration.current
//                val screenWidthDp = configuration.screenWidthDp.dp
//
//                ReceiptComponent(
//                    modifier = Modifier
//                        .width(screenWidthDp)
//                        .background(color = AppTheme.colorScheme.background1Neutral)
//                        .padding(16.dp),
//                    rowTypes = viewState.rowTypes,
//                    captureMode = true,
//                    headerContent = {
//                        ReceiptHeaderComponent(viewState)
//                    })
//            }
//        )
//    }

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
                showShareBottomSheet = false
            }
        )
    }
}

private val _colors = MutableStateFlow(lightColors())

private fun titleStyle() = TextStyle(
    color = _colors.value.onBackgroundNeutralSubdued,
    typography = AppTypography.bodySmall,
)

private fun amountStyle() = TextStyle(
    color = _colors.value.onBackgroundNeutralDefault,
    typography = AppTypography.headline3,
)

private fun currencyStyle() = TextStyle(
    color = _colors.value.onBackgroundNeutralDefault,
    typography = AppTypography.bodySmall,
)

private fun subtitleStyle() = TextStyle(
    color = _colors.value.onBackgroundNeutralDefault,
    typography = AppTypography.bodySmall,
)

@Preview
@Composable
fun ChooseChargePriceScreenPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ChargeReceiptScreen()
    }
}