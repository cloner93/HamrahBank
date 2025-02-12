package com.pmb.auth.presentaion.ekyc.authenticationSelectServices

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentaion.AuthScreens
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.utils.toCurrency

@Composable
fun AuthenticationSelectServicesScreen(navigationManager: NavigationManager) {
    var isContinueEnabled by remember {
        mutableStateOf(false)
    }
    var checked by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(false) }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.select_services),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BodySmallText(
                    text = stringResource(R.string.services_total, 500000.toDouble().toCurrency()),
                    color = AppTheme.colorScheme.onBackgroundPrimarySubdued
                )
                Spacer(modifier = Modifier.size(5.dp))
                AppTextButton(modifier = Modifier.weight(1f),
                    title = stringResource(R.string.show_details),
                    onClick = {
                        navigationManager.navigate(AuthScreens.FeeDetails)
                    })
            }
            Spacer(modifier = Modifier.size(10.dp))
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = checked || checked2,
                title = stringResource(R.string._continue),
                onClick = {
                    navigationManager.navigate(AuthScreens.openAccount)
                })
        }
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Headline6Text(
            text = stringResource(R.string.select_services_title),
            color = AppTheme.colorScheme.onBackgroundNeutralDefault,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(30.dp))
        BodySmallText(
            text = stringResource(R.string.select_services_description),
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(10.dp))
        RoundedCornerCheckbox(
            title = stringResource(R.string.activation_internet_bank),
            caption = stringResource(R.string.tax_amount, 20000.toDouble().toCurrency()),
            isChecked = checked
        ) {
            checked = !checked
        }
        Spacer(modifier = Modifier.size(10.dp))
        RoundedCornerCheckbox(
            title = stringResource(R.string.receive_internet_card),
            caption = stringResource(R.string.tax_amount, 20000.toDouble().toCurrency()),
            isChecked = checked2
        ) {
            checked2 = !checked2
        }
    }

}

@Composable
fun RoundedCornerCheckbox(
    title: String,
    caption: String? = null,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    size: Float = 18f,
    checkedColor: Color = AppTheme.colorScheme.foregroundPrimaryDefault,
    uncheckedBorderColor: Color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
    uncheckedColor: Color = Color.White,
    onValueChange: (Boolean) -> Unit
) {
    val checkboxColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedColor)
    val checkBorderColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedBorderColor)
    val density = LocalDensity.current
    val duration = 100

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier


    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(color = checkboxColor, shape = RoundedCornerShape(6.dp))
                .border(width = 1.5.dp, color = checkBorderColor, shape = RoundedCornerShape(6.dp))
                .toggleable(
                    value = isChecked,
                    role = Role.Checkbox,
                    onValueChange = onValueChange
                ),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = uncheckedColor
                )
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column {
            BodyMediumText(
                modifier = Modifier,
                text = title,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault

            )
            caption?.takeIf { it.isNotEmpty() }?.let {
                CaptionText(
                    modifier = Modifier,
                    text = it,
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )
            }
        }
    }
}

@Composable
fun RoundedCornerCheckbox(
    title: AnnotatedString,
    caption: String? = null,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    size: Float = 18f,
    checkedColor: Color = AppTheme.colorScheme.foregroundPrimaryDefault,
    uncheckedBorderColor: Color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
    uncheckedColor: Color = Color.White,
    onValueChange: (Boolean) -> Unit
) {
    val checkboxColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedColor)
    val checkBorderColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedBorderColor)
    val density = LocalDensity.current
    val duration = 100

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier


    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(color = checkboxColor, shape = RoundedCornerShape(6.dp))
                .border(width = 1.5.dp, color = checkBorderColor, shape = RoundedCornerShape(6.dp))
                .toggleable(
                    value = isChecked,
                    role = Role.Checkbox,
                    onValueChange = onValueChange
                ),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = uncheckedColor
                )
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column {

            BodyMediumText(
                modifier = Modifier,
                text = title,
                color = AppTheme.colorScheme.onBackgroundNeutralDefault

            )
            caption?.takeIf { it.isNotEmpty() }?.let {
                CaptionText(
                    modifier = Modifier,
                    text = it,
                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                )
            }
        }
    }
}