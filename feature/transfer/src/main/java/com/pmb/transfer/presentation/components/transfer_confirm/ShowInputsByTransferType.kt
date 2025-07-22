package com.pmb.transfer.presentation.components.transfer_confirm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.pmb.ballon.component.base.AppNumberTextField
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
                            defaultReason = defaultReason,
                            sourceAccountBanks = sourceAccountBanks,
                            selectedAccountBank = selectedAccountBank,
                            selectedTransferReason = selectedTransferReason
                        )
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