package com.pmb.transfer.presentation.transfer_receipt.viewmodel

import com.pmb.core.platform.BaseViewEvent

sealed interface TransferReceiptViewEvents : BaseViewEvent {
    data object ShareReceipt : TransferReceiptViewEvents
    data object SaveReceiptToGallery : TransferReceiptViewEvents
}