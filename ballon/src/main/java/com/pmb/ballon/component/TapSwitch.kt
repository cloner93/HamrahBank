package com.pmb.ballon.component

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.ButtonLargeText
import com.pmb.ballon.component.base.ButtonSmallText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun DynamicTabSelector(
    modifier: Modifier = Modifier,
    tabs: List<String>, // Can be 2 to 4 options
    selectedOption: Int = 0,
    containerColor: Color = AppTheme.colorScheme.foregroundNeutralDefault.copy(alpha = 0.4f),
    tabColor: Color = AppTheme.colorScheme.onForegroundNeutralDefault,
    selectedOptionColor: Color = AppTheme.colorScheme.onBackgroundNeutralActive,
    unselectedTextColor: Color = AppTheme.colorScheme.onForegroundNeutralDefault,
    containerCornerRadius: Dp = 16.dp,
    tabCornerRadius: Dp = 12.dp,
    selectorHeight: Dp = 48.dp,
    tabHeight: Dp = 40.dp,
    spacing: Dp = 4.dp,
    textStyle: TextStyle = TextStyle(
        color = Color(0xFF31394F).copy(alpha = 0.6f),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    selectedTabTextStyle: TextStyle = TextStyle(
        color = selectedOptionColor,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
    onTabSelected: (selectedIndex: Int) -> Unit = {}
) {
    if (tabs.size !in 2..4) {
        throw IllegalArgumentException("DynamicTabSelector must have between 2 and 4 options")
    }

    // Use BoxWithConstraints to get the width of the container
    BoxWithConstraints(
        modifier = modifier
            .clip(RoundedCornerShape(containerCornerRadius))
            .height(selectorHeight)
            .fillMaxSize()
            .background(containerColor)
    ) {
        val segmentWidth = maxWidth / tabs.size
        // Adjusted width for each tab, accounting for spacing
        val boxWidth = segmentWidth - spacing * 2
        val positions = tabs.indices.map { index ->
            segmentWidth * index + (segmentWidth - boxWidth) / 2
        }
        // Animate the X offset of the selected tab to smoothly transition between tabs
        val animatedOffsetX by animateDpAsState(targetValue = positions[selectedOption], label = "")
        // Determine the maximum height available for alignment
        val containerHeight = maxHeight
        // Center the tab selector vertically within the container
        val verticalOffset = (containerHeight - tabHeight) / 2

        Row(
            modifier = Modifier.fillMaxHeight(),
            // Ensures spacing between options
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, text ->
                ButtonSmallText(
                    text = text,
                    color = unselectedTextColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(segmentWidth)
                        .clickable(
                            indication = null,
                            // Avoids ripple effect for a cleaner look
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            // Trigger callback with the index of the selected tab
                            onTabSelected(index)
                        })
            }
        }
        // Selected tab highlighted by applying the selected tab text style
        Box(
            modifier = Modifier
                // Position the selector dynamically based on the selected tab
                .offset(x = animatedOffsetX, y = verticalOffset)
                .clip(RoundedCornerShape(tabCornerRadius))
                .width(boxWidth) // Updated box width
                .height(tabHeight)
                .background(tabColor)
        ) {
            ButtonLargeText(
                modifier = Modifier.align(Alignment.Center),
                text = tabs[selectedOption],
                color = AppTheme.colorScheme.foregroundNeutralDefault,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@AppPreview
@Composable
fun DynamicTabSelectorPreview() {
    HamrahBankTheme {
        /**
         * This preview demonstrates the use of remember { mutableStateOf(0) } to maintain the
         * selected tab's state across recompositions in DynamicTabSelector, enabling dynamic
         * UI updates in Jetpack Compose.
         **/
        val selectedOption = remember { mutableIntStateOf(0) }
        val optionTexts = listOf("Tab 1", "Tab 2", "Tab 3")
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .background(AppTheme.colorScheme.background1Neutral)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            DynamicTabSelector(tabs = optionTexts, selectedOption = selectedOption.intValue) {
                Toast.makeText(context, "Selected tab: ${it + 1}", Toast.LENGTH_SHORT).show()
                selectedOption.intValue = it
            }
        }
    }
}