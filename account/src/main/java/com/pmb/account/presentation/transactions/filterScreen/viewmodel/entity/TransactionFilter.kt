package com.pmb.account.presentation.transactions.filterScreen.viewmodel.entity

import android.os.Parcelable
import com.pmb.account.presentation.transactions.filterScreen.DateType
import com.pmb.account.presentation.transactions.filterScreen.TransactionType
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionFilter(
    val transactionType: TransactionType? = null,
    val fromPrice: String? = null,
    val toPrice: String? = null,
    val dateType: DateType? = null,
    val fromDate: String? = null,
    val toDate: String? = null,
) : Parcelable