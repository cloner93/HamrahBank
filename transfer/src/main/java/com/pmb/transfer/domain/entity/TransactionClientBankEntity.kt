package com.pmb.transfer.domain.entity

import android.os.Parcelable
import androidx.annotation.Keep
import com.pmb.core.platform.DomainModel
import kotlinx.parcelize.Parcelize


@Parcelize
@Keep
data class TransactionClientBankEntity(
    val clientBankEntity: ClientBankEntity,
    val type: BankIdentifierNumberType,
    val favorite: Boolean = false
) : DomainModel, Parcelable