package com.pmb.auth.presentation.component.cardComponent

import android.graphics.BitmapFactory
import androidx.compose.animation.core.animateDpAsState
import androidx.exifinterface.media.ExifInterface
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.io.File

@Composable
fun CardFlip(
    front: @Composable () -> Unit,
    back: @Composable () -> Unit,
    onClick: () -> Unit,
    isBack: Boolean
) {
    val rotation by animateFloatAsState(
        targetValue = if (isBack) 180f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "flipAnim"
    )

    val cameraDistancePx = with(LocalDensity.current) {
        30.dp.toPx()
    }

    Box(
        modifier = Modifier
            .graphicsLayer {
                rotationY = rotation
                this.cameraDistance = cameraDistancePx
            }
            .clickable {
                onClick.invoke()
            }

    ) {
        if (rotation <= 90f) {
            front()
        } else {
            Box(
                modifier = Modifier.graphicsLayer { rotationY = 180f }
            ) {
                back()
            }
        }
    }
}

@Composable
fun CardImage(
    imageAddress: Int,
    isHorizontal: Boolean,
    horizontalPadding: Dp = 40.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isHorizontal)
                    Modifier
                        .aspectRatio(1.5f)
                else
                    Modifier
                        .aspectRatio(0.9f)
                        .padding(horizontal = horizontalPadding)
            )
            .background(Color.Transparent, RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent, RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = imageAddress), contentDescription = null
        )
    }
}

@Composable
fun AsyncCardImage(
    imageAddress: File?, //todo it should change with image url from backend
    isHorizontal: Boolean,
) {
    Box(
        modifier = Modifier
//            .fillMaxWidth()
            .then(
                if (isHorizontal)
                    Modifier
                        .aspectRatio(3 / 2f)
                else
                    Modifier
                        .fillMaxWidth(0.5f)
                        .aspectRatio(2 / 3f)
//                        .padding(horizontal = horizontalPadding)
            )
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                    then (
//                    if (isHorizontal)
//                        Modifier
//                            .aspectRatio(3/2f)
//                    else
                    Modifier
                        .fillMaxSize()
//                            .aspectRatio(2/3f)
//                            .padding(horizontal = horizontalPadding)
                    )
                .clip(RoundedCornerShape(16.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageAddress)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.FillBounds,
//            placeholder = painterResource(imageAddress),
            contentDescription = "SliderImageHeader",
        )
    }
}

@Composable
fun AsyncInnerCardImage(
    imageFile: File?,
    cardWidth: Dp
) {
    val context = LocalContext.current

    // Determine orientation
    val isLandscape = remember(imageFile) {
        imageFile?.let { isImageActuallyHorizontal(it) } ?: true
    }

    // Animate width change between portrait/landscape
//    val animatedWidth by animateDpAsState(
//        targetValue = if (isLandscape) cardWidth else cardWidth * 0.5f,
//        label = "animatedCardWidth"
//    )

    val cardHeight = if (isLandscape) cardWidth * (2f / 3f) else cardWidth * (3f / 2f)

    Box(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageFile)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

fun isImageActuallyHorizontal(file: File): Boolean {
    val exif = ExifInterface(file.absolutePath)
    val orientation = exif.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_NORMAL
    )

    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }
    BitmapFactory.decodeFile(file.absolutePath, options)

    val width = options.outWidth
    val height = options.outHeight

    return when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90,
        ExifInterface.ORIENTATION_ROTATE_270 -> height >= width
        else -> width >= height
    }
}