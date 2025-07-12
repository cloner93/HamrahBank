package com.pmb.transfer.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.base.SubtitleSmallText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.transfer.R
import com.pmb.transfer.domain.entity.ReceiptStatus

@Composable
fun ReceiptStatusBadge(status: ReceiptStatus) {
    Card(
        shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(
            containerColor = when (status) {
                ReceiptStatus.SUCCESS -> AppTheme.colorScheme.foregroundSuccessDefault
                ReceiptStatus.FAILED -> AppTheme.colorScheme.foregroundErrorDefault
                ReceiptStatus.UNKNOWN -> AppTheme.colorScheme.foregroundWarningDefault
            }
        ), elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AppIcon(
                icon = IconType.Painter(
                    painterResource(
                        when (status) {
                            ReceiptStatus.SUCCESS -> com.pmb.ballon.R.drawable.ic_success_filled
                            ReceiptStatus.FAILED -> com.pmb.ballon.R.drawable.ic_error_filled
                            ReceiptStatus.UNKNOWN -> com.pmb.ballon.R.drawable.ic_warning_fill
                        }
                    )
                ), style = IconStyle(tint = AppTheme.colorScheme.onForegroundNeutralDefault)
            )
            Spacer(modifier = Modifier.width(6.dp))
            SubtitleSmallText(
                text = when (status) {
                    ReceiptStatus.SUCCESS -> stringResource(R.string.transfer_success)
                    ReceiptStatus.FAILED -> stringResource(R.string.payment_failed)
                    ReceiptStatus.UNKNOWN -> stringResource(R.string.transfer_unknown)
                },
                color = AppTheme.colorScheme.onForegroundNeutralDefault
            )
        }
    }
}
