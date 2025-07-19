package com.pmb.auth.presentation.component

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppImage

@Composable
fun PreviewRoundedImageComponent(
    modifier: Modifier = Modifier,
    fileUrl: String?,
    onClickListener: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(fileUrl)
                .crossfade(true)
                .build(),
            contentDescription = "SignatureImage",
            contentScale = ContentScale.Crop
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
                .size(48.dp),
            onClick = {
                onClickListener.invoke()

            }
        ) {
            AppImage(
                image = painterResource(com.pmb.ballon.R.drawable.ic_rounded_delete),
            )
        }
    }
}

@Composable
fun PreviewRoundedImageComponent(
    modifier: Modifier = Modifier,
    fileUrl: Uri?,
    onClickListener: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        if (fileUrl.toString().endsWith(".jpg") || fileUrl.toString().contains("image")) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(fileUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "SignatureImage",
                contentScale = ContentScale.FillBounds
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.ic_pdf),
                contentDescription = "PDF File",
                tint = Color.Unspecified,
                modifier = Modifier.size(156.dp)
            )
        }
        IconButton(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
                .size(48.dp),
            onClick = {
                onClickListener.invoke()

            }
        ) {
            AppImage(
                image = painterResource(com.pmb.ballon.R.drawable.ic_rounded_delete),
            )
        }
    }
}
