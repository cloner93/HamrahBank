package com.pmb.ballon.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.pmb.ballon.R
import com.pmb.ballon.ui.theme.AppTheme

@Composable
fun SentencesWithSuffix(
    sentence: String,
    suffix: String = stringResource(R.string.toman),
    sentenceStyle: com.pmb.ballon.models.TextStyle = com.pmb.ballon.models.TextStyle(
        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
        typography = AppTheme.typography.bodyMedium,
    ),
    suffixStyle: com.pmb.ballon.models.TextStyle = com.pmb.ballon.models.TextStyle(
        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
        typography = AppTheme.typography.headline6,
    )
) {
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
                        fontFamily = suffixStyle.typography?.fontFamily,
                        fontWeight = FontWeight.Normal,
                        color = suffixStyle.color,
                    )
                ) {
                    append(suffix)
                }
            },
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = sentenceStyle.color,
                fontFamily = sentenceStyle.typography?.fontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center // Centers text horizontally
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLongSentenceWithSuffix() {
    SentencesWithSuffix(sentence = "این یک جمله طولانی است که مبلغ آن محاسبه شده و در انتهای جمله تومان اضافه خواهد شد.")
}
