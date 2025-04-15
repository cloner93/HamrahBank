package com.pmb.ballon.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme

@SuppressLint("DefaultLocale")
@Composable
fun DynamicPassCardInputField(
    modifier: Modifier = Modifier,
    dyPass: String,
    retryEnabled: Boolean = false,
    timer: Int = 120,
    onValueChange: (String) -> Unit,
    onRetryDyPass: () -> Unit,
) {
    AppNumberTextField(
        modifier = modifier,
        value = dyPass,
        onValueChange = {
            onValueChange(it)
        },
        label = stringResource(R.string.second_pass),
        trailingIcon = {
            AppButton(
                modifier = Modifier.padding(horizontal = 8.dp),
                enable = retryEnabled,
                title = if (!retryEnabled) String.format(
                    "%02d:%02d",
                    timer / 60,
                    timer % 60
                ) else stringResource(R.string.get_dynamic_pass),
                textStyle = TextStyle.defaultButton()
                    .copy(typography = AppTheme.typography.buttonSmall),
                onClick = onRetryDyPass
            )
        },
    )
}
