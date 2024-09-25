package com.pmb.auth.presentaion.intro

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pmb.auth.R
import com.pmb.ballon.component.base.AppBottomSheet
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppOutlineButton
import com.pmb.ballon.component.base.AppText
import com.pmb.ballon.models.ButtonStyle
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTypography

@Composable
fun IntroScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var showBottomSheet by remember { mutableStateOf(false) }


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
                Image(
                    modifier = Modifier.size(128.dp),
                    painter = painterResource(com.pmb.ballon.R.drawable.img_mellat_logo),
                    contentDescription = "icon mellat logo"
                )

                Spacer(modifier = Modifier.size(16.dp))

                AppText(
                    title = stringResource(R.string.mellat_bank),
                    style = TextStyle(
                        color = Color.Black,
                        typography = AppTypography.headlineSmall
                    )
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
                    style = ButtonStyle(containerColor = Color.Black),
                    onClick = { navController.navigate("login") }
                )

                Spacer(modifier = Modifier.size(8.dp))

                AppOutlineButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.register),
                    style = ButtonStyle(containerColor = Color.Black),
                    onClick = {
//                        navController.navigate("register")
                        showBottomSheet = true
                    }
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
        }

    }

    AppBottomSheet(
        show = showBottomSheet,
        onDismiss = { showBottomSheet = false }) /*{

            Text(text = "This is a persistent bottom sheet")

            AppButton(title = "Collapse Sheet") {
                showBottomSheet = false
            }
        }*/
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