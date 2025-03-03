package com.pmb.auth.presentation.intro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.AuthScreens
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppOutlineButton
import com.pmb.core.presentation.NavigationManager

@Composable
fun IntroScreen(
    navigationManager: NavigationManager,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(modifier = modifier.matchParentSize()) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextImage(
                    image = com.pmb.ballon.R.drawable.img_mellat_logo,
                    text = stringResource(R.string.mellat_bank)
                )
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.login_to_hamrah_bank),
                    onClick = { navigationManager.navigate(AuthScreens.FirstLogin) }
                )

                Spacer(modifier = Modifier.size(8.dp))

                AppOutlineButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.register),
                    onClick = {
                        navigationManager.navigate(AuthScreens.Register)
                    }
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}


@Preview
@Composable
private fun SplashViewPreview() {

    Box {
        Column {
            Spacer(modifier = Modifier.fillMaxHeight())

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.login_to_hamrah_bank)
            ) {

            }

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.register)
            ) {
            }
        }

    }
}