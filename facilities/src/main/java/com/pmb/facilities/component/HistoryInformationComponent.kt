package com.pmb.facilities.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.models.AppButton

@Composable
fun HistoryInformationComponent(
    modifier: Modifier = Modifier,
    title: String,
    historyTitle: String,
    titleColor: Color,
    historyColor: Color,
    onHistoryClickListener: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BodyMediumText(
            text = title,
            color = titleColor
        )
        Spacer(modifier.weight(1f))
        AppTextButton(
            title = historyTitle,
            colors = AppButton.textButtonColors(
                contentColor = historyColor
            ),
            onClick = onHistoryClickListener
        )

    }
}