package com.pmb.transfer.presentation.transfer_receipt.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.TransferReceiptEntity

sealed interface TransferReceiptViewActions : BaseViewAction {
    data object ClearAlert : TransferReceiptViewActions
    data object Share : TransferReceiptViewActions
    data object SaveToGallery : TransferReceiptViewActions
    data class UpdateTransferReceipt(val transferReceipt: TransferReceiptEntity?) :
        TransferReceiptViewActions
    data object ShareReceiptClicked : TransferReceiptViewActions
    data object OnDismissShareReceiptBottomSheet : TransferReceiptViewActions
}