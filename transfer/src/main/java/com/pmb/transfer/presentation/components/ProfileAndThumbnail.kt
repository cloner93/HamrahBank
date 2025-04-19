package com.pmb.transfer.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ProfileAndThumbnail(
    profileUrl: String,
    icon: Painter,
    imageSize: Dp = 48.dp,
    iconSize: Dp = 22.dp
) {
    // Layering profile image and icon with Box
    Box(contentAlignment = Alignment.BottomEnd) {
        // Profile Image
        AsyncImage(
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(profileUrl)
                .allowHardware(false)
                .crossfade(true)
                .build(),
            placeholder = painterResource(com.pmb.ballon.R.drawable.profile),
            contentDescription = "ProfileImageHeader",
            contentScale = ContentScale.Crop
        )

        // Small Icon
        Image(
            modifier = Modifier
                .size(iconSize)
                .offset(x = 4.dp, y = 2.dp),
            painter = icon,
            contentDescription = "Logo Icon",
            contentScale = ContentScale.Crop,
        )
    }
}