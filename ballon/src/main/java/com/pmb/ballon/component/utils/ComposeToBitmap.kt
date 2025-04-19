package com.pmb.ballon.component.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.Ref
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.createBitmap


@Composable
fun ComposeToBitmap(
    modifier: Modifier = Modifier,
    onBitmapReady: (Bitmap) -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val viewRef = remember { Ref<ComposeView>() }

    AndroidView(
        modifier = modifier.size(1.dp), // خیلی کوچیک برای اینکه off-screen بمونه
        factory = {
            ComposeView(context).apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
                setContent {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        content.invoke()
                    }
                }
                viewRef.value = this
            }
        },
        update = { view ->
            view.post {
                view.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                )
                view.layout(0, 0, view.measuredWidth, view.measuredHeight)

                if (view.measuredWidth > 0 && view.measuredHeight > 0) {
                    val bitmap = createBitmap(
                        view.measuredWidth,
                        view.measuredHeight
                    ).copy(Bitmap.Config.ARGB_8888, true) // ⛑ مهم برای جلوگیری از hardware bitmap
                    try {
                        val canvas = Canvas(bitmap)
                        view.draw(canvas)
                        onBitmapReady(bitmap)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    )
}