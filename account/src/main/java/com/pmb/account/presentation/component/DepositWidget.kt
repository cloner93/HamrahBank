package com.pmb.account.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline2Text
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.core.utils.toCurrency
import java.util.Locale

data class DepositModel(
    val title: String,
    val depositNumber: String,
    val amount: Double,
    val currency: String,
    val ibanNumber: String,
    val cardNumber: String,
    var isSelected: Boolean = false
)

@Composable
fun DepositWidget(
    modifier: Modifier = Modifier,
    item: DepositModel,
    isAmountVisible: Boolean,
    moreClick: () -> Unit,
    onAmountVisibilityClick: () -> Unit,
    onDepositListChipsClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.background1Neutral),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .background(color = AppTheme.colorScheme.background1Neutral),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppButtonIcon(
                    icon = Icons.Outlined.MoreHoriz,
                    onClick = moreClick
                )
                AppButtonIcon(
                    icon = if (isAmountVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                    onClick = onAmountVisibilityClick
                )
                Spacer(modifier = Modifier.weight(1f))

                ChipWithIcon(
                    value = "سپرده ها",
                    startIcon = Icons.Default.ArrowDropDown,
                    startIconStyle = IconStyle(
                        tint = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        size = Size.FIX(18.dp)
                    ),
                    clickable = onDepositListChipsClick,
                    assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            BodyMediumText(
                text = item.depositNumber,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
            Spacer(modifier = Modifier.height(8.dp))
            CaptionText(modifier = Modifier.padding(horizontal = 8.dp), text = item.title)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isAmountVisible) {
                    Headline2Text(text = item.amount.toCurrency())
                    Spacer(modifier = Modifier.width(6.dp))
                    BodySmallText(text = item.currency)
                } else {
                    Headline2Text(text = "********")
                }
            }
        }
    }
}

/*@Composable
fun ChipWithIcon(
    modifier: Modifier = Modifier,
    value: String,
    startIcon: ImageVector? = null,
    startIconStyle: IconStyle = IconStyle(),
    endIcon: ImageVector? = null,
    endIconStyle: IconStyle = IconStyle(),
    clickable: () -> Unit,
    roundedShape: Dp = 16.dp,
    color: Color = AppTheme.colorScheme.backgroundTintPrimaryDefault,
    assetColor: Color = AppTheme.colorScheme.onBackgroundTintPrimaryDefault,
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .height(32.dp)
            .clip(shape = RoundedCornerShape(roundedShape))
            .background(color = color)
            .clickable { clickable.invoke() }
            .padding(horizontal = 10.dp, vertical = 6.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (startIcon != null) {
                AppIcon(
                    icon = startIcon,
                    style = startIconStyle
                )
                Spacer(modifier = Modifier.size(6.dp))
            }
            BodySmallText(text = value, color = assetColor)
            if (endIcon != null) {
                Spacer(modifier = Modifier.size(6.dp))
                AppIcon(
                    icon = endIcon,
                    style = endIconStyle
                )
            }
        }
    }
}*/


@Preview
@Composable
private fun DepositPrev() {
    val dip = DepositModel(
        title = "حساب قرض الحسنه",
        depositNumber = "1232324-56",
        amount = 10000023400.0,
        currency = stringResource(R.string.real_carrency),
        ibanNumber = "IR1234567890098765432112",
        cardNumber = "6219861920241234",
    )
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl) {
        HamrahBankTheme {

            DepositWidget(
                item = dip,
                isAmountVisible = true,
                moreClick = {},
                onAmountVisibilityClick = { }
            ) { }
        }
    }
}

@Preview
@Composable
private fun DepositPrev2() {
    val dip = DepositModel(
        title = "حساب قرض الحسنه",
        depositNumber = "1232324-56",
        amount = 10000023400.0,
        currency = stringResource(R.string.real_carrency),
        ibanNumber = "IR1234567890098765432112",
        cardNumber = "6219861920241234",
    )
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl,
        LocalConfiguration provides Configuration().apply {
            setLocale(Locale("fa"))

        }) {
        HamrahBankTheme {

            DepositWidget(
                item = dip,
                isAmountVisible = false,
                moreClick = {},
                onAmountVisibilityClick = { }
            ) { }
        }
    }
}