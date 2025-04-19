package com.pmb.ballon.models

import androidx.compose.ui.unit.LayoutDirection

sealed class RowType(open val title: String, open val textStyle: TextStyle) {

    data class SimpleTextRow(
        override val title: String,
        override val textStyle: TextStyle
    ) : RowType(title = title, textStyle = textStyle)

    data class TwoTextRow(
        override val title: String,
        val subtitle: String,
        override val textStyle: TextStyle,
        val subtitleStyle: TextStyle,
        val subtitleLayoutDirection: LayoutDirection = LayoutDirection.Rtl
    ) : RowType(title = title, textStyle = textStyle)

    data class PaymentRow(
        override val title: String,
        val amount: String,
        val currency: String,
        override val textStyle: TextStyle,
        val amountStyle: TextStyle,
        val currencyStyle: TextStyle
    ) : RowType(title = title, textStyle = textStyle)
}