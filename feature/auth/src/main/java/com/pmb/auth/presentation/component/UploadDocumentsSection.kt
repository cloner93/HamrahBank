package com.pmb.auth.presentation.component

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun UploadDocumentsSection(
    modifier: Modifier = Modifier,
    images: Uri?,
    onAddClicked: () -> Unit,
    onRemoveImage: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "بارگذاری مدارک",
            style = AppTheme.typography.buttonLarge,
            color = AppTheme.colorScheme.onBackgroundNeutralDefault
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                images.takeIf { it !=null }?.let { uri ->
                    Log.d("photo",images.toString())
                    PreviewRoundedImageComponent(
                        modifier = Modifier
                            .size(156.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .border(
                                1.dp,
                                AppTheme.colorScheme.strokeNeutral3Rest,
                                RoundedCornerShape(16.dp)
                            ), fileUrl = uri
                    ) {
                        onRemoveImage()
                    }
                } ?: run {
                    AddDocumentItem(onClick = onAddClicked)
                }


            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}



@Composable
fun AddDocumentItem(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(156.dp)
            .clip(RoundedCornerShape(16.dp))
            .dashedBorder(
                color = AppTheme.colorScheme.strokeNeutral1Default,
                strokeWidth = 1.dp,
                dashLength = 2.dp,
                gapLength = 8.dp,
                cornerRadius = 16.dp
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Document",
            tint = AppTheme.colorScheme.onBackgroundNeutralDefault,
            modifier = Modifier.size(36.dp)
        )
    }
}

fun Modifier.dashedBorder(
    color: Color,
    strokeWidth: Dp = 1.dp,
    dashLength: Dp = 6.dp,
    gapLength: Dp = 4.dp,
    cornerRadius: Dp = 16.dp
): Modifier = this.then(
    Modifier.drawBehind {
        val stroke = Stroke(
            width = strokeWidth.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(dashLength.toPx(), gapLength.toPx()), 0f
            )
        )
        val width = size.width
        val height = size.height

        drawRoundRect(
            color = color,
            topLeft = Offset.Zero,
            size = Size(width, height),
            style = stroke,
            cornerRadius = CornerRadius(cornerRadius.toPx())
        )
    }
)