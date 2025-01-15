package com.pmb.transfer.presentation.components.transfer_confirm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
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
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.transfer.R

@Composable
fun ShowInputsAccount(
    depositId: String, onDepositIdChange: (String) -> Unit
) {
    BodyMediumText(text = stringResource(R.string.withdraw_from_account))
    Spacer(modifier = Modifier.size(8.dp))
    MenuItem(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(color = Color.Transparent)
        .border(
            border = BorderStroke(1.dp, AppTheme.colorScheme.strokeNeutral1Default),
            shape = RoundedCornerShape(12.dp)
        ),
        horizontalPadding = 12.dp,
        title = "حساب قرض الحسنه (۳۷۳۸۰۸۶۳)",
        subtitle = "قابل برداشت: ۷۲.۴۶۵.۰۰۰ ریال",
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
        onItemClick = {

        })

    Spacer(modifier = Modifier.size(24.dp))
    MenuItem(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(color = Color.Transparent)
        .border(
            border = BorderStroke(1.dp, AppTheme.colorScheme.strokeNeutral1Default),
            shape = RoundedCornerShape(12.dp)
        ),
        horizontalPadding = 12.dp,
        title = stringResource(R.string.cause_of_transfer),
        endIcon = com.pmb.ballon.R.drawable.ic_drrow_down,
        titleStyle = TextStyle(
            color = AppTheme.colorScheme.foregroundNeutralDefault,
            typography = AppTheme.typography.bodyMedium
        ),
        endIconStyle = IconStyle(
            tint = AppTheme.colorScheme.onBackgroundNeutralDefault, size = Size.FIX(24.dp)
        ),
        onItemClick = {

        })
    Spacer(modifier = Modifier.size(24.dp))
    AppNumberTextField(
        value = depositId,
        label = stringResource(R.string.deposit_id_optional),
        onValueChange = {
            if (it.isDigitsOnly()) onDepositIdChange.invoke(it)
        })
}