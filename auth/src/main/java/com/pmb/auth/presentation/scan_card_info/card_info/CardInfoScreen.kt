package com.pmb.auth.presentation.scan_card_info.card_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.AuthScreens
import com.pmb.auth.presentation.scan_card_info.card_info.viewModel.CardInfoViewEvents
import com.pmb.auth.presentation.scan_card_info.card_info.viewModel.CardInfoViewModel
import com.pmb.auth.utils.ComingType
import com.pmb.ballon.component.DynamicPassCardInputField
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppButtonWithIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.home.presentation.HomeScreens

@Composable
fun CardInfoScreen(
    navigationManager: NavigationManager,
    viewModel: CardInfoViewModel,
    comingType: ComingType
) {
    val viewState by viewModel.viewState.collectAsState()
    var cardNumber by remember {
        mutableStateOf("")
    }
    var cvv2 by remember { mutableStateOf("") }
    var isCvv2 by remember { mutableStateOf(false) }
    var isCard by remember { mutableStateOf(false) }
    var pass2 by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var retryEnabled by remember { mutableStateOf(true) }
    var isPass2 by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                CardInfoViewEvents.SendCardInfoSucceed -> {
                    navigationManager.navigate(HomeScreens.Home)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.bank_card_information),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = !viewState.isLoading && isCard && isPass2 && isCvv2,
                title = stringResource(R.string._continue),
                onClick = {
                    if (comingType == ComingType.COMING_PASSWORD) {
                        navigationManager.navigateAndClearStack(AuthScreens.ForgetPassword)
                        navigationManager.setCurrentScreenData<ComingType>(
                            "authentication",
                            comingType
                        )
                    } else {
                        navigationManager.navigateAndClearStack(AuthScreens.AuthenticationConfirmStep)
                        navigationManager.setCurrentScreenData<ComingType>(
                            "authentication",
                            comingType
                        )
                    }
                })
        }
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        BodyMediumText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.enter_your_card_info),
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(24.dp))
        AppNumberTextField(
            value = cardNumber,
            label = stringResource(R.string.card_number),
        ) { input ->
            val rawText =
                input.filter { it.isLetterOrDigit() } // Remove spaces and keep only alphanumeric


            if (rawText.length == 16) {
                val formattedText = rawText.chunked(4).reversed()
                    .joinToString(" ")// Prevents extra spaces beyond the expected length
                cardNumber = formattedText
            } else {
                cardNumber = rawText
            }
            isCard = cardNumber.length >= 16
        }
        Spacer(modifier = Modifier.size(24.dp))

        AppButtonWithIcon(
            modifier = Modifier
                .fillMaxWidth(),
            title = stringResource(R.string.card_scan),
            icon = com.pmb.ballon.R.drawable.ic_scan,
            enable = false
        ) {

        }
        Spacer(modifier = Modifier.size(24.dp))
        AppNumberTextField(
            modifier = Modifier.fillMaxWidth(),
            value = cvv2,
            label = "CVV2",
            trailingIcon = {},
            onValueChange = {
                if (it.length <= 4) cvv2 = it
                isCvv2 = cvv2.length in 3..4
            },
        )
        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 56.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            AppNumberTextField(
                modifier = Modifier
                    .weight(0.45f),
                value = month,
                label = stringResource(com.pmb.ballon.R.string.month),
                bordered = true,
                enabled = true,
                trailingIcon = {},
                onValueChange = {
                    if (it.length <= 2 && it.toIntOrNull() in 0..12 || it.isEmpty())
                        month = it
                }
            )
            Spacer(modifier = Modifier.weight(0.1f))
            AppNumberTextField(
                modifier = Modifier
                    .weight(0.45f),
                value = year,
                label = stringResource(com.pmb.ballon.R.string.year),
                bordered = true,
                enabled = true,
                trailingIcon = {},
                onValueChange = { if (it.length <= 2) year = it }
            )

        }
        Spacer(modifier = Modifier.size(24.dp))
        DynamicPassCardInputField(
            dyPass = pass2,
            retryEnabled = retryEnabled,
            onValueChange = {
                pass2 = it
                isPass2 = it.length >= 4
            },
            onRetryDyPass = {
                retryEnabled = !retryEnabled
            })
        Spacer(modifier = Modifier.size(30.dp))


    }
}