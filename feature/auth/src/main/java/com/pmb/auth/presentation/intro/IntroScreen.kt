package com.pmb.auth.presentation.intro

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppOutlineButton
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.AuthScreens
import com.pmb.navigation.moduleScreen.RegisterScreens

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFBE7EB), Color.White),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 2000f)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.45f)
        ) {
            drawCircle(
                color = Color.White,
                radius = 418.dp.toPx(),
                center = Offset(46.dp.toPx() + 418.dp.toPx(), (-256).dp.toPx() + 418.dp.toPx())
            )
        }
        Column(modifier = modifier.matchParentSize()) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AppImage(
                    image = painterResource(com.pmb.ballon.R.drawable.img_mellat_logo),
                    style = ImageStyle(size = Size.FIX(all = 128.dp))
                )
                Spacer(modifier = Modifier.size(8.dp))
                AppImage(
                    image = painterResource(com.pmb.ballon.R.drawable.ic_mellat_description),
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
                    title = stringResource(R.string.account_opening),
                    onClick = {
                        navigationManager.navigate(RegisterScreens.Preparation)
                    }
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}