package com.pmb.ballon.component.text_field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.pmb.ballon.R
import com.pmb.ballon.component.TextIcon
import com.pmb.ballon.component.base.AppBaseTextField
import com.pmb.ballon.component.base.AppButtonIcon
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.PasswordValidationResult
import com.pmb.core.utils.isPassword

@Composable
fun AppPasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    conditionMessage: Boolean = false,
    onValidate: ((result: PasswordValidationResult) -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf(PasswordValidationResult()) }
    var isFocused by remember { mutableStateOf(false) }

    Column {
        AppBaseTextField(
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            value = value,
            label = label,
            singleLine = true,
            onValueChange = {
                result = it.isPassword()
//                if (it.length <= 8 || result.isValid)
                onValueChange(it)
                onValidate?.invoke(result)
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = @Composable {
                AppButtonIcon(icon = if (passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility) {
                    passwordVisible = !passwordVisible
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (isFocused && conditionMessage) {
            Spacer(modifier = Modifier.size(24.dp))
            PasswordConditions(result = result)
        }
    }
}

@Composable
private fun PasswordConditions(result: PasswordValidationResult) {
    val unchecked = R.drawable.ic_dash_circle_2
    val checked = R.drawable.ic_check_circle
    Column {
        BodySmallText(
            text = stringResource(R.string.title_pasword_condition),
            color = AppTheme.colorScheme.onBackgroundNeutralSubdued
        )
        Spacer(modifier = Modifier.size(12.dp))
        TextIcon(
            title = stringResource(R.string.min_char_in_password),
            icon = if (result.minLen) checked else unchecked
        )
        TextIcon(
            title = stringResource(R.string.upper_case),
            icon = if (result.uppercase) checked else unchecked
        )
        TextIcon(
            title = stringResource(R.string.lowercase),
            icon = if (result.lowercase) checked else unchecked
        )
        TextIcon(
            title = stringResource(R.string.digit),
            icon = if (result.digit) checked else unchecked
        )
        TextIcon(
            title = stringResource(R.string.special_char),
            icon = if (result.specialChar) checked else unchecked
        )
    }
}