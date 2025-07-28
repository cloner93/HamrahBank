package com.pmb.profile.presentaion.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ProfileHeader(profileUrl: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier.clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(profileUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(com.pmb.ballon.R.drawable.profile_placeholder),
            error = painterResource(com.pmb.ballon.R.drawable.profile_placeholder),
            contentDescription = "ProfileImageHeader",
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.size(16.dp))
//        AppText(title = title);
    }
}


@Preview
@Composable
fun ProfileHeaderPreview() {
    ProfileHeader(
        profileUrl = "https://gama.ir/uploads/user/avatars/avatar_AiP0QACGVS7w3O5JH9Z3.jpg",
        title = "I am Pooriak Khalaj"
    )
}