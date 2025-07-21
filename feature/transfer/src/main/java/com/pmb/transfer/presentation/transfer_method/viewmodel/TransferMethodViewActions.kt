package com.pmb.transfer.presentation.transfer_method.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.TransferMethodEntity

sealed interface TransferMethodViewActions : BaseViewAction {
    data class SetPaymentTypes(
        val methods: List<TransferMethodEntity>
    ) : TransferMethodViewActions

    data class SelectPaymentType(
        val transferMethod: TransferMethodEntity
    ) : TransferMethodViewActions

    data object ClearAlert : TransferMethodViewActions
}