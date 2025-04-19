package com.pmb.ballon.component.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmb.ballon.models.AppTextField
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.appFontFamily
import com.pmb.core.utils.isIranianNationalId
import com.pmb.core.utils.isMobile
import java.text.DecimalFormat

@Composable
fun AppBaseTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester = remember { FocusRequester() },
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onFocused: ((Boolean) -> Unit)? = null,
    bordered: Boolean = true,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    textStyle: TextStyle = TextStyle.Default.copy(
        fontSize = 16.sp,
        fontFamily = appFontFamily
    ),
    interactionSource: MutableInteractionSource? = null,
    onClick: (() -> Unit)? = null
) {
    var isFocused by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
            .focusRequester(focusRequester)
            .clickable(enabled = onClick != null) {
                onClick?.let {
                    onClick()
                }
            }
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
                onFocused?.invoke(isFocused)
            },
        value = value,
        enabled = if (readOnly) false else enabled,
        onValueChange = { input ->
            onValueChange(input)
        },
        label = {
            label?.let {
                BodyMediumText(
                    text = it,
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = textStyle,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (bordered) AppTextField.defaultColors().focusedIndicatorColor else Color.Transparent,
            disabledBorderColor = if (bordered) AppTextField.defaultColors().disabledIndicatorColor else Color.Transparent,
            unfocusedBorderColor = if (bordered) AppTextField.defaultColors().unfocusedIndicatorColor else Color.Transparent,
            focusedLabelColor = AppTextField.defaultColors().focusedLabelColor,
            unfocusedLabelColor = AppTextField.defaultColors().unfocusedLabelColor,
            cursorColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
            errorBorderColor = AppTextField.defaultColors().errorIndicatorColor,
            errorLabelColor = AppTextField.defaultColors().errorLabelColor,
            disabledLabelColor = AppTextField.defaultColors().disabledLabelColor,
            errorTextColor = AppTextField.defaultColors().errorTextColor,
            disabledTextColor = AppTextField.defaultColors().disabledTextColor,
            focusedTextColor = AppTextField.defaultColors().focusedTextColor,
            unfocusedTextColor = AppTextField.defaultColors().unfocusedTextColor
        ),
        maxLines = maxLines,
        minLines = minLines,
        singleLine = singleLine,
        shape = RoundedCornerShape(12.dp),
        interactionSource = interactionSource
    )
}

@Composable
fun AppSingleTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit
) {
    AppBaseTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = label,
        singleLine = true,
        readOnly = readOnly,
        onValueChange = onValueChange,
        trailingIcon = @Composable {
            if (value.isNotEmpty()) AppButtonIcon(icon = Icons.Default.Close) { onValueChange("") }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun AppMultiTextField(
    modifier: Modifier = Modifier, value: String, label: String, onValueChange: (String) -> Unit
) {
    AppBaseTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = label,
        onValueChange = onValueChange,
        trailingIcon = @Composable {
            if (value.isNotEmpty()) AppButtonIcon(icon = Icons.Default.Close) { onValueChange("") }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun AppClickableReadOnlyTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    enabled: Boolean = true,
    trailingIcon: @Composable () -> Unit = @Composable {},
    onClick: () -> Unit,
) {
    AppBaseTextField(
        modifier = modifier,
        value = value,
        label = label,
        onValueChange = {}, // Read-only: Do nothing on value change
        readOnly = true,    // Makes the field read-only
        enabled = enabled,
        trailingIcon = trailingIcon,
        onClick = onClick,
    )
}


@Composable
fun AppMobileTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValidate: ((Boolean) -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    AppBaseTextField(
        modifier = modifier.fillMaxWidth(),
        value = value.trim(),
        label = label,
        singleLine = true,
        onValueChange = {
            val result = it.isMobile()
            if (it.length <= result.length || result.isValid) onValueChange(it)
            onValidate?.invoke(result.isValid)
        },
        trailingIcon = @Composable {
            if (value.isNotEmpty()) AppButtonIcon(icon = Icons.Default.Close) { onValueChange("") }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
    )
}

@Composable
fun AppNationalIdTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValidate: ((Boolean) -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    AppBaseTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = label,
        singleLine = true,
        onValueChange = {
            val valid = it.isIranianNationalId()
            if (it.length <= 10 || valid) onValueChange(it)
            onValidate?.invoke(valid)
        },
        trailingIcon = @Composable {
            if (value.isNotEmpty()) AppButtonIcon(icon = Icons.Default.Close) { onValueChange("") }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun AppNumberTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    bordered: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    showClearButton: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    focusRequester: FocusRequester = remember { FocusRequester() },
    textStyle: TextStyle = AppTheme.typography.bodyMedium.copy(
        textDirection = TextDirection.Ltr
    ),
    onFocused: ((Boolean) -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    val _trallingIcon: @Composable (() -> Unit)? =
        trailingIcon ?: if (showClearButton && value.isNotEmpty()) {
            {
                AppButtonIcon(
                    icon = Icons.Default.Close,
                    onClick = { onValueChange("") })
            }
        } else trailingIcon
    AppBaseTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = label,
        enabled = enabled,
        singleLine = true,
        bordered = bordered,
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        textStyle = textStyle,
        onValueChange = { newText ->
            // Ensure that the input consists of only digits
            onValueChange(newText.filter { c -> c.isDigit() })
        },
        onFocused = onFocused,
        trailingIcon = _trallingIcon,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}


@Composable
fun AppSearchTextField(
    modifier: Modifier = Modifier,
    query: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        BasicTextField(
            value = query,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .then(
                    if (isFocused) {
                        Modifier.border(
                            width = 1.dp,
                            color = AppTheme.colorScheme.onBackgroundNeutralSubdued,
                            shape = RoundedCornerShape(12.dp)
                        )
                    } else {
                        Modifier // No border when not focused
                    }
                )
                .background(
                    color = if (isFocused) Color.Transparent else AppTheme.colorScheme.background3Neutral,
                    shape = RoundedCornerShape(12.dp)
                ),
            singleLine = true,
            textStyle = AppTheme.typography.bodyMedium.copy(color = Color.Black),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = AppTheme.colorScheme.onBackgroundNeutralSubdued
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        if (query.isEmpty()) {
                            BodyMediumText(
                                text = hint,
                                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                            )
                        }
                        innerTextField()
                    }
                    if (query.isNotEmpty()) {
                        AppButtonIcon(
                            icon = IconType.ImageVector(imageVector = Icons.Outlined.Close),
                            onClick = { onValueChange("") })
                    }
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAnimatedTextField() {
    AppBaseTextField(
        label = "username",
        value = "askjhjkhas",
        onValueChange = { },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchTextField() {
    AppSearchTextField(
        query = "username",
        hint = "salam",
        onValueChange = { },
    )
}


// VisualTransformation for showing commas in TextField
class CommaVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text

        val digitsOnly = originalText.filter { it.isDigit() }
        val formattedText = digitsOnly.toLongOrNull()?.let {
            DecimalFormat("#,###").format(it)
        } ?: ""

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // Map original to transformed (with commas)
                var commas = 0
                var i = 0
                while (i < offset && i < digitsOnly.length) {
                    if ((digitsOnly.length - i) % 3 == 0 && i != 0) commas++
                    i++
                }
                return offset + commas
            }

            override fun transformedToOriginal(offset: Int): Int {
                // Map transformed back to original (ignore commas)
                var digitsSeen = 0
                var i = 0
                while (digitsSeen < offset && i < formattedText.length) {
                    if (formattedText[i] != ',') digitsSeen++
                    i++
                }
                return digitsSeen
            }
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}
