package com.pmb.ballon.component.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.loadingState
import com.pmb.ballon.models.IconStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme

@Composable
fun ChipWithIcon(
    modifier: Modifier = Modifier,
    value: String,
    startIcon: ImageVector? = null,
    startIconStyle: IconStyle = IconStyle(),
    endIcon: ImageVector? = null,
    endIconStyle: IconStyle = IconStyle(),
    clickable: (() -> Unit)? = null,
    roundedShape: Dp = 16.dp,
    color: Color = AppTheme.colorScheme.backgroundTintPrimaryDefault,
    assetColor: Color = AppTheme.colorScheme.onBackgroundTintPrimaryDefault,
    borderColor: Color? = null,
    borderWidth: Dp = 1.dp,
    fillMaxWidth: Boolean = false,
    isLoading: Boolean = false
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .height(32.dp)
            .clip(RoundedCornerShape(roundedShape))
            .background(color)
            .then(
                if (borderColor != null)
                    Modifier.border(borderWidth, borderColor, RoundedCornerShape(roundedShape))
                else
                    Modifier
            )
            .loadingState(isLoading)
            .clickable(enabled = clickable != null) { clickable?.invoke() }
            .padding(horizontal = 10.dp, vertical = 6.dp),
    ) {
        Row(
            modifier = if (fillMaxWidth) Modifier.fillMaxWidth() else Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (startIcon != null) {
                AppIcon(icon = startIcon, style = startIconStyle)
                Spacer(modifier = Modifier.width(6.dp))
            }

            BodySmallText(text = value, color = assetColor)

            if (endIcon != null) {
                Spacer(modifier = Modifier.width(6.dp))
                AppIcon(icon = endIcon, style = endIconStyle)
            }
        }
    }
}

@AppPreview
@Composable
fun PreviewChipDefault() {
        HamrahBankTheme {
            Column(
                modifier = Modifier.background(AppTheme.colorScheme.background1Neutral),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChipWithIcon(
                    modifier = Modifier,
                    value = "Default Chip",
                    clickable = { },
                    color = Color.LightGray,
                    assetColor = Color.Black,
                    fillMaxWidth = true
                )
                ChipWithIcon(
                    value = "Default Chip",
                    startIcon = Icons.Default.ArrowDropDown,
                    clickable = { },
                    color = Color.LightGray,
                    assetColor = Color.Black
                )
                ChipWithIcon(
                    value = "Default Chip",
                    startIcon = Icons.Default.ArrowDropDown,
                    clickable = { },
                    color = Color.LightGray,
                    assetColor = Color.Black,
                    isLoading = true
                )

                ChipWithIcon(
                    value = "Bordered Chip",
                    startIcon = Icons.Default.ArrowDropDown,
                    clickable = { },
                    color = Color.White,
                    assetColor = Color.Black,
                    borderColor = Color.Gray
                )


                ChipWithIcon(
                    value = "With End Icon",
                    startIcon = Icons.Default.ArrowDropDown,
                    endIcon = Icons.Default.Check,
                    clickable = { },
                    color = Color.Blue.copy(alpha = 0.1f),
                    assetColor = Color.Blue
                )
            }
        }


}
