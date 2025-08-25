package com.pmb.transfer.presentation.transfer_verify_card_info.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.CardVerificationEntity

sealed interface TransferVerifyCardInfoViewActions : BaseViewAction {
    data object ClearAlert : TransferVerifyCardInfoViewActions
    data object SubmitCardInfo : TransferVerifyCardInfoViewActions
    data object ResendCardInfo : TransferVerifyCardInfoViewActions
    data class UpdateCardInfo(val cardBank: CardBankEntity) : TransferVerifyCardInfoViewActions
    data class TransferVerify(val verificationInfo: CardVerificationEntity) :
        TransferVerifyCardInfoViewActions

    data class UpdateDyPassword(val dyPassword: String) : TransferVerifyCardInfoViewActions
    data class UpdateCvv2(val cvv2: String) : TransferVerifyCardInfoViewActions
    data object StartTimer : TransferVerifyCardInfoViewActions
    data object CancelTimer : TransferVerifyCardInfoViewActions
}