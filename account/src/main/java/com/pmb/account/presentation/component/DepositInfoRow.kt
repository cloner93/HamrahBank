package com.pmb.account.presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun DepositInfoRow(title: String, startIcon: Int, onClick: () -> Unit) {
    MenuItem(
        modifier = Modifier.height(52.dp),
        title = title,
        horizontalPadding = 16.dp,
        startIcon = startIcon,
        endIcon = R.drawable.ic_arrow_left,
        titleStyle = TextStyle(
            color = Color(0xFF42474E),
            typography = AppTheme.typography.headline6
        ),
        startIconStyle = IconStyle(tint = AppTheme.colorScheme.iconColor),
        endIconStyle = IconStyle(tint = Color(0xFFC2C7CF)),
        clickable = false,
        onItemClick = onClick
    )
}