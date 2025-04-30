package com.pmb.transfer.presentation.transfer_receipt.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.receipt.model.RowData
import com.pmb.transfer.domain.entity.TransferReceiptEntity

data class TransferReceiptViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val receipt: TransferReceiptEntity? = null,
    val rows: List<RowData> = emptyList(),
    val showShareBottomSheet: Boolean = false
) : BaseViewState
