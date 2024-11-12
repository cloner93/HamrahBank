package com.pmb.transfer.presentation.amount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.TopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.utils.Convert
import com.pmb.transfer.R
import com.pmb.transfer.domain.TransactionClientBank
import com.pmb.transfer.presentation.TransferScreens

@Composable
fun AmountScreen(navigationManager: NavigationManager) {
    var isValid by remember { mutableStateOf(false) }
    var identifierNumber by remember { mutableStateOf("") }
    var clientBank by remember { mutableStateOf<TransactionClientBank?>(null) }

    Box(modifier = Modifier.background(color = AppTheme.colorScheme.background1Neutral)) {
        AppContent(topBar = TopBar(title = stringResource(R.string.destination),
            onBack = { navigationManager.navigateBack() }), footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
                title = stringResource(R.string.next),
                enable = isValid,
                onClick = {
                    navigationManager.navigate(TransferScreens.TransferMethod)
                })
        }) {
            AppNumberTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = identifierNumber,
                onValueChange = {
                    identifierNumber = it
                    isValid = if (it.isNotEmpty() && it.isDigitsOnly())
                        it.toBigInteger() >= 10.toBigInteger() else false
                },
                label = stringResource(R.string.amount),
            )

            Spacer(modifier = Modifier.size(16.dp))

            if (isValid)
                LongSentenceWithSuffix(
                    sentence = Convert.numberToWords(identifierNumber.toBigInteger()),
                    suffix = stringResource(R.string.toman)
                )
        }
    }
}


@Composable
fun LongSentenceWithSuffix(sentence: String, suffix: String = stringResource(R.string.toman)) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center // Centers the content within the Box
    ) {
        Text(
            text = buildAnnotatedString {
                append(sentence)
                append(" ")
                withStyle(
                    style = SpanStyle(
                        fontFamily = AppTheme.typography.bodyMedium.fontFamily,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append(suffix)
                }
            },
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = AppTheme.typography.headline6.fontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center // Centers text horizontally
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLongSentenceWithSuffix() {
    LongSentenceWithSuffix(sentence = "این یک جمله طولانی است که مبلغ آن محاسبه شده و در انتهای جمله تومان اضافه خواهد شد.")
}
