package com.pmb.transfer.presentation.components.transfer_confirm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.MenuItemDefaults
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.PaymentType
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.entity.TransferSourceEntity
import com.pmb.transfer.domain.entity.asAccount
import com.pmb.transfer.domain.entity.asCard


@Composable
fun ShowInputsByTransferType(
    transferMethod: TransferMethodEntity,
    depositId: String,
    sourceCardBanks: List<CardBankEntity>?,
    sourceAccountBanks: List<AccountBankEntity>?,
    defaultSource: TransferSourceEntity?,
    defaultReason: ReasonEntity?,
    onDepositIdChange: (String) -> Unit,
    selectedCardBank: (CardBankEntity) -> Unit,
    selectedAccountBank: (AccountBankEntity) -> Unit,
    selectedTransferReason: (ReasonEntity?) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        when (transferMethod.paymentType) {
            PaymentType.CARD_TO_CARD ->
                if (sourceCardBanks != null)
                    defaultSource.asCard()?.let { defaultCard ->
                        CartBanksComponent(
                            defaultCardBank = defaultCard,
                            sourceCardBanks = sourceCardBanks,
                            selectedCardBank = selectedCardBank
                        )
                    }

            PaymentType.MELLAT_TO_MELLAT,
            PaymentType.INTERNAL_SATNA,
            PaymentType.INTERNAL_PAYA,
            PaymentType.INTERNAL_BRIDGE ->
                if (sourceAccountBanks != null)
                    defaultSource.asAccount()?.let { defaultAccount ->
                        AccountBanksComponent(
                            defaultAccountBank = defaultAccount,
                            sourceAccountBanks = sourceAccountBanks,
                            selectedAccountBank = selectedAccountBank,
                        )
                    }

        }

        when (transferMethod.paymentType) {
            PaymentType.CARD_TO_CARD,
            PaymentType.MELLAT_TO_MELLAT -> Unit
            PaymentType.INTERNAL_SATNA,
            PaymentType.INTERNAL_PAYA,
            PaymentType.INTERNAL_BRIDGE -> {
                Spacer(modifier = Modifier.size(24.dp))
                MenuItem(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = Color.Transparent)
                        .border(
                            border = BorderStroke(1.dp, AppTheme.colorScheme.strokeNeutral1Default),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    title = defaultReason?.title ?: stringResource(R.string.cause_of_transfer),
                    endIcon = com.pmb.ballon.R.drawable.ic_drrow_down,
                    titleStyle = TextStyle(
                        color = AppTheme.colorScheme.foregroundNeutralDefault,
                        typography = AppTheme.typography.bodyMedium
                    ),
                    innerPadding = MenuItemDefaults.innerPadding.copy(start = 16.dp),
                    endIconStyle = IconStyle(
                        tint = AppTheme.colorScheme.onBackgroundNeutralDefault,
                        size = Size.FIX(24.dp)
                    ),
                    onItemClick = {
                        selectedTransferReason.invoke(defaultReason)
                    })
            }
        }

        Spacer(modifier = Modifier.size(24.dp))
        AppNumberTextField(
            value = depositId,
            label = stringResource(R.string.deposit_id_optional),
            onValueChange = {
                if (it.isDigitsOnly()) onDepositIdChange.invoke(it)
            })

    }
}