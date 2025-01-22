package com.pmb.auth.presentaion.ekyc.signature

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.auth.R
import com.pmb.auth.presentaion.ekyc.signature.viewModel.SignatureViewModel
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun SignatureScreen(
    viewModel: SignatureViewModel = hiltViewModel()
) {
//    val state by viewModel.state.collectAsState()
//    LaunchedEffect(Unit) {
//        viewModel.processAction(CameraActions.RequestCameraPermission)
//    }
//    LaunchedEffect(Unit) {
//        viewModel.processAction(CameraActions.RequestFilePermission)
//    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.account_signature),
                onBack = { }
            )
        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = true,
                title = stringResource(R.string._continue),
                onClick = {
                })
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(24.dp))

        BodyMediumText(
            text = stringResource(R.string.take_your_signature),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued

        )
        BodySmallText(
            text = stringResource(R.string.coverage_signature_message),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued
        )


    }

}

@Preview(showBackground = true)
@Composable
fun SignatureScreenPreview() {
    SignatureScreen()
}
