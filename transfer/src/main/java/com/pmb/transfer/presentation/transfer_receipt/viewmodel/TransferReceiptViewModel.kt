package com.pmb.transfer.presentation.transfer_receipt.viewmodel

import com.pmb.ballon.models.RowType
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTypography
import com.pmb.ballon.ui.theme.lightColors
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.utils.Convert
import com.pmb.core.utils.toCurrency
import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.utils.BankUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

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
                        title = "شخص انتقال دهنده",
                        subtitle = receipt.senderName,
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
                        title = when (receipt.account.type) {
                            BankIdentifierNumberType.CARD -> "کارت مبدأ"
                            BankIdentifierNumberType.ACCOUNT,
                            BankIdentifierNumberType.IBAN -> "سپرده مبداء"
                        },
                        subtitle =
                            when (receipt.account.type) {
                                BankIdentifierNumberType.CARD -> BankUtil.formatMaskCardNumber(
                                    receipt.account.clientBankEntity.cardNumber
                                )

                                BankIdentifierNumberType.ACCOUNT,
                                BankIdentifierNumberType.IBAN -> BankUtil.formatMaskAccountBankNumber(
                                    receipt.account.clientBankEntity.accountNumber
                                )
                            },
                        textStyle = titleStyle(),
                        subtitleStyle = subtitleStyle(),
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