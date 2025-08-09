package com.pmb.receipt.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.LayoutDirection
import com.pmb.ballon.models.RowType
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.AppTypography
import com.pmb.ballon.ui.theme.CustomColors


@Composable
fun RowData.mapToRowType(
    styleProvider: RowStyleProvider = DefaultRowStyleProvider(
        ReceiptStyle(AppTheme.colorScheme)
    )
): RowType {
    return when (this) {
        is RowData.Simple -> RowType.SimpleTextRow(
            title = title,
            textStyle = styleProvider.titleStyle
        )

        is RowData.TwoText -> RowType.TwoTextRow(
            title = title,
            subtitle = subtitle,
            textStyle = styleProvider.titleStyle,
            subtitleStyle = styleProvider.subtitleStyle,
            subtitleLayoutDirection = subtitleLayoutDirection
        )

        is RowData.Payment -> RowType.PaymentRow(
            title = title,
            amount = amount,
            currency = currency,
            textStyle = styleProvider.titleStyle,
            amountStyle = styleProvider.amountStyle,
            currencyStyle = styleProvider.currencyStyle
        )
    }
}


sealed class RowData(open val title: String) {
    data class Simple(override val title: String) : RowData(title)

    data class TwoText(
        override val title: String,
        val subtitle: String,
        val subtitleLayoutDirection: LayoutDirection = LayoutDirection.Rtl
    ) : RowData(title)

    data class Payment(
        override val title: String,
        val amount: String,
        val currency: String
    ) : RowData(title)
}

interface RowStyleProvider {
    val titleStyle: TextStyle
    val subtitleStyle: TextStyle
    val amountStyle: TextStyle
    val currencyStyle: TextStyle
}

class DefaultRowStyleProvider(style: ReceiptStyle) : RowStyleProvider {
    override val titleStyle = style.titleStyle
    override val subtitleStyle = style.subtitleStyle
    override val amountStyle = style.amountStyle
    override val currencyStyle = style.currencyStyle
}


class ReceiptStyle(colorScheme: CustomColors) {
    val titleStyle = TextStyle(
        color = colorScheme.onBackgroundNeutralSubdued,
        typography = AppTypography.bodySmall,
    )

    val amountStyle = TextStyle(
        color = colorScheme.onBackgroundNeutralDefault,
        typography = AppTypography.headline3,
    )

    val currencyStyle = TextStyle(
        color = colorScheme.onBackgroundNeutralDefault,
        typography = AppTypography.bodySmall,
    )

    val subtitleStyle = TextStyle(
        color = colorScheme.onBackgroundNeutralDefault,
        typography = AppTypography.bodySmall,
    )
}
