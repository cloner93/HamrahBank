package com.pmb.ballon.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.BaseAppText
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme


@Composable
fun BaseItemColumn(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    bottomDivider: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.Center
    ) {
        title()
        Spacer(modifier = Modifier.size(4.dp))
        subtitle()
        if (bottomDivider) HorizontalDivider()
    }
}

@Composable
fun ItemColumn(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    bottomDivider: Boolean = false,
    titleStyle: TextStyle = TextStyle(
        color = Color(0xFF42474E),
        typography = AppTheme.typography.bodyMedium,
    ),
    subtitleStyle: TextStyle = TextStyle(
        color = Color(0xFF42474E),
        typography = AppTheme.typography.caption,
    )
) {
    BaseItemColumn(
        modifier = modifier,
        title = { BaseAppText(title = title, style = titleStyle) },
        subtitle = { BaseAppText(title = subtitle, style = subtitleStyle) },
        bottomDivider = bottomDivider
    )
}

@Preview
@Composable
private fun InvoiceItemColumnPreview() {
    ItemColumn(title = "کارمزد ۱", subtitle = "200,000")
}

