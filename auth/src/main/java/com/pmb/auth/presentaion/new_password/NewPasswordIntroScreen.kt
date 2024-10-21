package com.pmb.auth.presentaion.new_password

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pmb.auth.R
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.TopBar

@Composable
fun NewPasswordIntroScreen(navController: NavController) {
    AppContent(
        modifier = Modifier.padding(24.dp),
        topBar = TopBar(
            title = stringResource(R.string.select_new_password),
            onBack = { navController.navigateUp() }),
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                title = stringResource(R.string._continue),
                onClick = { navController.navigate("new_password") })
        },
        content = {
            Box(contentAlignment = Alignment.Center) {
                TextImage(
                    image = com.pmb.ballon.R.drawable.img_key,
                    text = stringResource(R.string.msg_select_new_password),
                )
            }
        })
}