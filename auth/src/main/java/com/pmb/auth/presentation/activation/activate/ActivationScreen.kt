package com.pmb.auth.presentation.activation.activate

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.AuthScreens
import com.pmb.auth.presentation.activation.activate.viewModel.ActivationViewActions
import com.pmb.auth.presentation.activation.activate.viewModel.ActivationViewEvents
import com.pmb.auth.presentation.activation.activate.viewModel.ActivationViewModel
import com.pmb.auth.presentation.component.RoundedCornerCheckboxComponent
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.component.base.AppNationalIdTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager

@Composable
fun ActivationScreen(navigationManager: NavigationManager, viewModel: ActivationViewModel) {
    var mobile by remember { mutableStateOf("") }
    var nationalId by remember { mutableStateOf("") }
    val viewState by viewModel.viewState.collectAsState()
    var isMobile by remember { mutableStateOf(false) }
    var isNationalId by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    BackHandler() {
        if (showBottomSheet) {
            showBottomSheet = false
        } else {
            navigationManager.navigateBack()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                ActivationViewEvents.ActiveUserSucceed -> {
                    navigationManager.navigate(AuthScreens.ActivationTaxDetailsScreen)
                }
            }
        }
    }
    if (!showBottomSheet) {
        AppContent(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            topBar = {
                AppTopBar(
                    title = stringResource(R.string.activation),
                    onBack = { navigationManager.navigateBack() })
            },
            footer = {
                RoundedCornerCheckboxComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clickable {
                            showBottomSheet = true
                        },
                    title = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Normal,
                                color = AppTheme.colorScheme.foregroundPrimaryDefault,
                            )
                        ) {
                            append(stringResource(R.string.usage_rules))
                        }
                        append(" ")
                        append(stringResource(R.string.rules_read_and_accepted))
                    },
                    isChecked = viewState.isChecked
                ) {
                    showBottomSheet = true

                }
                Spacer(modifier = Modifier.size(10.dp))
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    title = stringResource(R.string._continue),
                    enable = !viewState.loading && isMobile && viewState.isChecked,
                    onClick = {
                        viewModel.handle(
                            ActivationViewActions.ActiveUser(
                                mobileNumber = mobile,
                                nationalId = nationalId,
                            )
                        )
                    })

            },
            content = {
                AppImage(
                    modifier = Modifier.size(56.dp),
                    image = painterResource(com.pmb.ballon.R.drawable.img_mellat_logo),
                )

                Spacer(modifier = Modifier.size(32.dp))

                AppMobileTextField(
                    value = mobile,
                    label = stringResource(R.string.mobile_number),
                    onValidate = { isMobile = it },
                    onValueChange = { mobile = it })

                Spacer(modifier = Modifier.size(24.dp))

                AppNationalIdTextField(
                    value = nationalId,
                    label = stringResource(R.string.national_id),
                    onValidate = { isNationalId = it },
                    onValueChange = { nationalId = it })

                Spacer(modifier = Modifier.height(24.dp))

            })
        if (viewState.loading) {
            AppLoading()
        }
        if (viewState.alertModelState != null) {
            AlertComponent(viewState.alertModelState!!)
        }
    } else {
        AppContent(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            topBar = {
                AppTopBar(
                    title = stringResource(R.string.usage_rules), startIcon = ClickableIcon(
                        icon = IconType.ImageVector(Icons.Default.Close),
                        onClick = {
                            showBottomSheet = false
                        })
                )
            }, footer = {
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.accept),
                    onClick = {
                        showBottomSheet = false
                        viewModel.handle(ActivationViewActions.SelectRules)
                    })
            }) {
            BodyMediumText(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.usage_role_desc),
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
        }
    }
}


