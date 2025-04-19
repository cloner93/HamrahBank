package com.pmb.transfer.presentation.transfer_receipt.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.LayoutDirection
import com.pmb.ballon.models.RowType
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTypography
import com.pmb.ballon.ui.theme.lightColors
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.utils.Convert
import com.pmb.core.utils.toCurrency
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.domain.entity.TransferSourceEntity
import com.pmb.transfer.utils.BankUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import androidx.core.graphics.createBitmap

@HiltViewModel
class TransferReceiptViewModel @Inject constructor() :
    BaseViewModel<TransferReceiptViewActions, TransferReceiptViewState, TransferReceiptViewEvents>(
        TransferReceiptViewState()
    ) {
    override fun handle(action: TransferReceiptViewActions) {
        when (action) {
            TransferReceiptViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            TransferReceiptViewActions.SaveToGallery -> postEvent(TransferReceiptViewEvents.SaveReceiptToGallery)
            TransferReceiptViewActions.Share -> postEvent(TransferReceiptViewEvents.ShareReceipt)
            is TransferReceiptViewActions.UpdateTransferReceipt -> handleUpdateTransferReceipt(
                action.transferReceipt
            )
        }
    }

    private fun handleUpdateTransferReceipt(receipt: TransferReceiptEntity?) {
        receipt ?: return
        setState {
            it.copy(
                receipt = receipt,
                rowTypes = listOf(
                    RowType.PaymentRow(
                        title = "مبلغ انتقال",
                        amount = receipt.amount.toCurrency(),
                        currency = "ریال",
                        textStyle = titleStyle(),
                        amountStyle = amountStyle(),
                        currencyStyle = currencyStyle(),
                    ),
                    RowType.TwoTextRow(
                        title = "زمان",
                        subtitle = Convert.timestampToPersianDate(receipt.date),
                        textStyle = titleStyle(),
                        subtitleStyle = subtitleStyle(),
                    ),
                    RowType.TwoTextRow(
                        subtitle = when (receipt.source) {
                            is TransferSourceEntity.Account -> receipt.source.account.accountHolderName
                            is TransferSourceEntity.Card -> receipt.source.card.cardHolderName
                        },
                        title = "شخص انتقال دهنده",
                        textStyle = titleStyle(),
                        subtitleStyle = subtitleStyle(),
                    ),
                    RowType.TwoTextRow(
                        title = "روش انتقال",
                        subtitle = receipt.paymentType.value,
                        textStyle = titleStyle(),
                        subtitleStyle = subtitleStyle(),
                    ),
                    RowType.TwoTextRow(
                        title = when (receipt.source) {
                            is TransferSourceEntity.Account -> "سپرده مبداء"
                            is TransferSourceEntity.Card -> "کارت مبدأ"
                        },
                        subtitle =
                            when (receipt.source) {
                                is TransferSourceEntity.Account ->
                                    BankUtil.formatMaskAccountBankNumber(receipt.source.account.accountNumber)

                                is TransferSourceEntity.Card ->
                                    BankUtil.formatMaskCardNumber(receipt.source.card.cardNumber)
                            },
                        textStyle = titleStyle(),
                        subtitleStyle = subtitleStyle(),
                        subtitleLayoutDirection = LayoutDirection.Ltr
                    ),
                    RowType.TwoTextRow(
                        title = "شماره پیگیری",
                        subtitle = receipt.trackingNumber.toString(),
                        textStyle = titleStyle(),
                        subtitleStyle = subtitleStyle(),
                    )
                )
            )
        }
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
