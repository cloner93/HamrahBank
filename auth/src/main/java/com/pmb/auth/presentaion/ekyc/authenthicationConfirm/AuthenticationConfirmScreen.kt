package com.pmb.auth.presentaion.ekyc.authenthicationConfirm

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentaion.AuthScreens
import com.pmb.auth.presentaion.ekyc.authenthicationConfirm.viewModel.AuthenticationConfirmStepViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager

@Composable
fun AuthenticationConfirmScreen(
    navigationManager: NavigationManager,
    viewModel: AuthenticationConfirmStepViewModel
) {
    val viewState by viewModel.viewState.collectAsState()
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.authentication),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = viewState.data?.authenticationStepConfirmList?.isNotEmpty() == true,
                title = stringResource(R.string.confirm),
                onClick = {
                    navigationManager.navigate(AuthScreens.AuthenticationSelectServices)
                })
        }
    ) {
        viewState.data?.authenticationStepConfirmList?.takeIf { it.isNotEmpty() }
            ?.let { authenticationObject ->
                Spacer(modifier = Modifier.size(16.dp))
                Headline6Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.check_authentication_data),
                    color = AppTheme.colorScheme.strokeNeutral2Active
                )
                Spacer(modifier = Modifier.size(8.dp))
                BodyMediumText(
                    text = stringResource(R.string.result_authentication_info),
                    color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    authenticationObject.forEachIndexed { index, authenticationStepConfirmObject ->
                        ConfirmationStepComponent(
                            modifier = Modifier,
                            text = authenticationStepConfirmObject.title,
                            isStepPassed = if (index + 1 < authenticationObject.size) {
                                authenticationObject[index + 1].isEnabled
                            } else false,
                            isEnabled = authenticationStepConfirmObject.isEnabled,
                            isDrawLine = index < authenticationObject.size-1
                        )
                    }
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


@Composable
fun Circle(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colorScheme.foregroundPrimaryDefault
) {
    Box(
        modifier = modifier.drawBehind {
            drawCircle(color = color)
        }
    )
}

@Composable
fun Line(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colorScheme.foregroundPrimaryDefault
) {
    Canvas(modifier = modifier) {
        drawLine(
            color = color,
            start = Offset.Zero,
            end = Offset(0f, size.height),
            strokeWidth = 3.dp.toPx()
        )
    }
}

@Composable
fun ConfirmationStepComponent(
    modifier: Modifier = Modifier,
    text: String,
    isStepPassed: Boolean = false,
    isEnabled: Boolean = true,
    isDrawLine: Boolean = true,
    textDisabledColor: Color = AppTheme.colorScheme.onBackgroundPrimaryDisabled,
    textEnabledColor: Color = AppTheme.colorScheme.foregroundNeutralDefault,
    infoEnabledColor: Color = AppTheme.colorScheme.foregroundPrimaryDefault,
    infoDisabledColor: Color = AppTheme.colorScheme.onBackgroundPrimaryDisabled
) {
    Row(modifier = modifier) {
        Column(
            modifier = Modifier.heightIn(min = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Circle(
                modifier = Modifier.size(20.dp),
                color = if (isEnabled) infoEnabledColor else infoDisabledColor
            )
            if (isDrawLine)
                Line(
                    modifier = Modifier.height(30.dp),
                    color = if (isEnabled && isStepPassed) infoEnabledColor else infoDisabledColor
                )
        }
        Spacer(modifier = Modifier.size(10.dp))
        BodySmallText(
            text = text,
            color = if (isEnabled) textEnabledColor else textDisabledColor
        )
    }
}

