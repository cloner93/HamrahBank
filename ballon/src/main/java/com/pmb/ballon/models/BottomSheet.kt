package com.pmb.ballon.models

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmb.ballon.ui.theme.AppTheme

@Stable
@ExperimentalMaterial3Api
object AppBottomSheetDefaults {
    private val DockedDragHandleWidth = 55.0.dp
    private val DockedDragHandleHeight = 3.0.dp

    /** The optional visual marker placed on top of a bottom sheet to indicate it may be dragged. */
    @Composable
    fun DragHandle(
        modifier: Modifier = Modifier,
        width: Dp = DockedDragHandleWidth,
        height: Dp = DockedDragHandleHeight,
        shape: Shape = MaterialTheme.shapes.extraLarge,
        color: Color = AppTheme.colorScheme.strokeNeutral3Rest,
    ) {
        BottomSheetDefaults.DragHandle(
            modifier = modifier,
            width = width,
            height = height,
            shape = shape,
            color = color,
        )
    }
}