package com.pmb.ballon.models

sealed class RowType(open val title: String, open val textStyle: TextStyle) {
    data class SimpleTextRow(override val title: String, override val textStyle: TextStyle) :
        RowType(title = title, textStyle = textStyle)

    data class TwoTextRow(
        override val title: String,
        val subtitle: String,
        override val textStyle: TextStyle,
        val subtitleStyle: TextStyle
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