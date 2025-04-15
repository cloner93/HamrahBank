package com.pmb.account.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.ButtonMediumText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun CustomAppTopBar(
    modifier: Modifier = Modifier,
    requiredHeight: Boolean = true,
    startIcon: ClickableIcon? = null,
    endIcon: ClickableIcon? = null,
    middleContent: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (requiredHeight)
                    modifier.height(64.dp)
                else
                    Modifier
            ),
    ) {
        if (startIcon != null) {
            AppButtonIcon(
                modifier = Modifier
                    .align(Alignment.Companion.CenterStart),
                icon = startIcon.icon,
                onClick = startIcon.onClick
            )
        }
        if (endIcon != null) {
            AppButtonIcon(
                modifier = Modifier
                    .align(Alignment.Companion.CenterEnd),
                icon = endIcon.icon,
                onClick = endIcon.onClick
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.Companion.Center)
        ) {
            middleContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomAppTopBarPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        CustomAppTopBar(
            startIcon = ClickableIcon(
                IconType.ImageVector(Icons.Filled.ArrowForward),
                onClick = {
                },
            ),
            endIcon = ClickableIcon(
                IconType.ImageVector(Icons.Filled.Search),
                onClick = {
                },
            ),
            middleContent = {
                ButtonMediumText(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "فیلترها",
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomAppTopBar2Preview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        CustomAppTopBar(
            startIcon = ClickableIcon(
                IconType.ImageVector(Icons.Filled.ArrowForward),
                onClick = {
                },
            ),
            endIcon = ClickableIcon(
                IconType.ImageVector(Icons.Filled.Search),
                onClick = {
                },
            ),
            middleContent = {
                ChipWithIcon(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = AppTheme.colorScheme.strokeNeutral1Default,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    roundedShape = 12.dp,
                    value = "لیست کارت ها",
                    startIcon = Icons.Default.ArrowDropDown,
                    clickable = {
                    },
                    color = Color.Transparent,
                    assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomAppTopBar3Preview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        CustomAppTopBar(
            middleContent = {
                ChipWithIcon(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = AppTheme.colorScheme.strokeNeutral1Default,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    roundedShape = 12.dp,
                    value = "لیست کارت ها",
                    startIcon = Icons.Default.ArrowDropDown,
                    clickable = {
                    },
                    color = Color.Transparent,
                    assetColor = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            }
        )
    }
}
