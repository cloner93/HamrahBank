package com.pmb.auth.presentation.component.cardComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme

@Composable
fun FlipToggle(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Icon(
//            painter = painterResource(R.drawable.ric_swipe_phone),
//            contentDescription = null,
//            tint = AppTheme.colorScheme.onBackgroundNeutralDefault
//        )
//        Spacer(modifier = Modifier.width(4.dp))
        Text(
            style = AppTheme.typography.headline6,
            text = stringResource(R.string.tap_on_card),
            color = AppTheme.colorScheme.onBackgroundNeutralDefault
        )
    }
}

@Preview
@Composable
fun FlipTogglePreview() {
    var selected by remember { mutableStateOf(false) }

    HamrahBankTheme {
        FlipToggle(){

        }
    }
}