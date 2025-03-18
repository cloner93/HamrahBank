package com.pmb.ballon.component

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

@Composable
fun DynamicPassCardInputField(
    modifier: Modifier = Modifier,
    dyPass: String,
    retryEnabled: Boolean = false,
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
                title = stringResource(R.string.get_dynamic_pass),
                textStyle = TextStyle.defaultButton()
                    .copy(typography = AppTheme.typography.buttonSmall),
                onClick = onRetryDyPass
            )
        },
    )
}
