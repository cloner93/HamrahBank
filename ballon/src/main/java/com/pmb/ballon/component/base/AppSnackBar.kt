package com.pmb.ballon.component.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun AppSnackBar(data: SnackbarData) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(
                color = AppTheme.colorScheme.foregroundNeutralDefault,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            AppIcon(
                modifier = Modifier.padding(end = 12.dp),
                icon = painterResource(id = R.drawable.ic_check_circle),
                style = IconStyle(
                    tint = AppTheme.colorScheme.foregroundSuccessDefault,
                    size = Size.FIX(24.dp)
                )
            )
            Column(modifier = Modifier.weight(1f)) {
                BodySmallText(
                    text = data.visuals.message,
                    color = AppTheme.colorScheme.onForegroundNeutralDefault,
                )
            }
//                        IconButton(onClick = { data.dismiss() }) {
//                            Icon(
//                                imageVector = Icons.Default.Close,
//                                contentDescription = "Dismiss",
//                                tint = Color.White
//                            )
//                        }
        }
    }
}
