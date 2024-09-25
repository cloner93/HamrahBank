package com.pmb.auth.presentaion.foget_password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pmb.auth.R
import com.pmb.ballon.component.MenuItem
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.BottomSheetScaffoldExample
import com.pmb.ballon.component.base.TopBar

@Composable
fun ForgetPasswordAuthScreen(navController: NavController) {
    AppContent(
        modifier = Modifier.padding(24.dp),
        topBar = TopBar(
            title = stringResource(R.string.authentication),
            onBack = { navController.navigateUp() })
    ) {
        TextImage(
            modifier = Modifier.fillMaxWidth(),
            image = com.pmb.ballon.R.drawable.img_key,
            text = stringResource(R.string.msg_forget_password_auth)
        )
        Spacer(modifier = Modifier.weight(1f))

        MenuItem(
            title = stringResource(R.string.mellat_signiture_app),
            endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
            bottomDivider = true,
            onItemClick = {

            })
        MenuItem(
            title = stringResource(R.string.video_authentication_process),
            endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
            bottomDivider = true,
            onItemClick = {

            })
        MenuItem(
            title = stringResource(R.string.bank_card_information),
            endIcon = com.pmb.ballon.R.drawable.ic_arrow_left,
            onItemClick = {
            })

        Spacer(modifier = Modifier.weight(1f))
    }

    Column(modifier = Modifier.fillMaxWidth().height(500.dp)) {
        BottomSheetScaffoldExample()
    }
}