package com.pmb.auth.presentation.ekyc.open_account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.ekyc.open_account.viewModel.OpenAccountViewActions
import com.pmb.auth.presentation.ekyc.open_account.viewModel.OpenAccountViewEvents
import com.pmb.auth.presentation.ekyc.open_account.viewModel.OpenAccountViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.RoundedCornerCheckboxComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.HomeScreens

@Composable
fun OpenAccountScreen(
    viewModel: OpenAccountViewModel
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                OpenAccountViewEvents.OpenAccountSucceed -> {
                    navigationManager.navigate(HomeScreens.Home)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.open_account),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = viewState.isChecked,
                title = stringResource(R.string.confirm_open),
                onClick = {
                    viewModel.handle(OpenAccountViewActions.OpenAccountConfirm)
                })
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            AppImage(
                image = painterResource(com.pmb.ballon.R.drawable.ic_open_account),
            )
            Spacer(modifier = Modifier.size(16.dp))
            RoundedCornerCheckboxComponent(
                title = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            color = AppTheme.colorScheme.foregroundPrimaryDefault,
                        )
                    ) {
                        append(stringResource(R.string.rules))
                    }
                    append(" ")
                    append(stringResource(R.string.rules_read_and_accepted))
                },
                isChecked = viewState.isChecked
            ) {
                viewModel.handle(OpenAccountViewActions.SelectRules)
            }
        }
    }
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}