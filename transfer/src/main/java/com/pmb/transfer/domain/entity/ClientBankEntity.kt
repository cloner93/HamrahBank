package com.pmb.transfer.domain.entity

import android.os.Parcelable
import androidx.annotation.Keep
import com.pmb.core.platform.DomainModel
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class ClientBankEntity(
    val name: String,
    val phoneNumber: String,
    val profileUrl: String,
    val cardNumber: String,
    val accountNumber: String,
    val iban: String
) : DomainModel, Parcelable