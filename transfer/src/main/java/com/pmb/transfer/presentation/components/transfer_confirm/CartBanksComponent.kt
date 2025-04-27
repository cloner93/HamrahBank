package com.pmb.transfer.presentation.components.transfer_confirm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.MenuItemDefaults
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.CardBankEntity

@Composable
fun CartBanksComponent(
    defaultCardBank: CardBankEntity,
    sourceCardBanks: List<CardBankEntity>,
    selectedCardBank: (CardBankEntity) -> Unit,
) {
    var showCardsBottomSheet by remember { mutableStateOf(false) }

    BodyMediumText(text = stringResource(R.string.withdraw_from_card))
    Spacer(modifier = Modifier.size(8.dp))
    MenuItem(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.Transparent)
            .border(
                border = BorderStroke(1.dp, AppTheme.colorScheme.strokeNeutral1Default),
                shape = RoundedCornerShape(12.dp)
            ),
        title = defaultCardBank.cardNumberFormated + " کارت",
        subtitle = "قابل برداشت: ${defaultCardBank.cardBalance.toCurrency()} ریال",
        endIcon = com.pmb.ballon.R.drawable.ic_drrow_down,
        titleStyle = TextStyle(
            color = AppTheme.colorScheme.foregroundNeutralDefault,
            typography = AppTheme.typography.bodyMedium
        ),
        subtitleStyle = TextStyle(
            color = AppTheme.colorScheme.onBackgroundNeutralDefault,
            typography = AppTheme.typography.caption
        ),
        endIconStyle = IconStyle(
            tint = AppTheme.colorScheme.onBackgroundNeutralDefault, size = Size.FIX(24.dp)
        ),
        titleLayoutDirection = LayoutDirection.Ltr,
        onItemClick = { showCardsBottomSheet = true })

    if (showCardsBottomSheet)
        CardBanksBottomSheet(
            defaultCardBank = defaultCardBank,
            items = sourceCardBanks,
            onItemSelected = { selectedCardBank.invoke(it) },
            onDismiss = {
                showCardsBottomSheet = false
            })

}
