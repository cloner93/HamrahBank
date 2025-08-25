package com.pmb.transfer.presentation.transfer_receipt.viewmodel

import androidx.compose.ui.unit.LayoutDirection
import com.pmb.calender.formatInReceipt
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.utils.toCurrency
import com.pmb.receipt.model.RowData
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.domain.entity.TransferSourceEntity
import com.pmb.transfer.utils.BankUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
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

            TransferReceiptViewActions.ShareReceiptClicked ->
                setState { it.copy(showShareBottomSheet = true) }

            TransferReceiptViewActions.OnDismissShareReceiptBottomSheet ->
                setState { it.copy(showShareBottomSheet = false) }
        }
    }

    private fun handleUpdateTransferReceipt(receipt: TransferReceiptEntity?) {
        receipt ?: return
        receipt.source ?: return
        setState {
            it.copy(
                receipt = receipt,
                rows = listOf(
                    RowData.Payment(
                        title = "مبلغ انتقال",
                        amount = receipt.amount.toCurrency(),
                        currency = "ریال",
                    ),
                    RowData.TwoText(
                        title = "زمان",
                        subtitle = Date().formatInReceipt(),
                    ),
                    RowData.TwoText(
                        subtitle = when (receipt.source) {
                            is TransferSourceEntity.Account -> receipt.source.account.accountHolderName
                                ?: ""

                            is TransferSourceEntity.Card -> receipt.source.card.cardHolderName
                        },
                        title = "شخص انتقال دهنده",
                    ),
                    RowData.TwoText(
                        title = "روش انتقال",
                        subtitle = receipt.paymentType.value,
                    ),
                    RowData.TwoText(
                        title = when (receipt.source) {
                            is TransferSourceEntity.Account -> "سپرده مبداء"
                            is TransferSourceEntity.Card -> "کارت مبدأ"
                        },
                        subtitle =
                            when (receipt.source) {
                                is TransferSourceEntity.Account ->
                                    BankUtil.formatMaskAccountBankNumber(
                                        receipt.source.account.accountNumber ?: ""
                                    )

                                is TransferSourceEntity.Card ->
                                    BankUtil.formatMaskCardNumber(receipt.source.card.cardNumber)
                            },
                        subtitleLayoutDirection = LayoutDirection.Ltr
                    ),
                    RowData.TwoText(
                        title = "شماره پیگیری",
                        subtitle = receipt.trackingNumber.toString(),
                    )
                )
            )
        }
    }
}
