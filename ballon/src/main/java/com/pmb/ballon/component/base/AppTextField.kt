package com.pmb.ballon.component.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmb.ballon.models.AppTextField
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.isIranianNationalId
import com.pmb.core.utils.isMobile


@Composable
fun AppBaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit) = @Composable {},
    isError: Boolean = false,
    onFocused: ((Boolean) -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = AppTextField.defaultColors(),
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }


    val borderColor = if (isError) AppTheme.colorScheme.strokeNeutral2Error
    else if (!enabled) Color.Transparent
    else if (isFocused) AppTheme.colorScheme.strokeNeutral2Active
    else AppTheme.colorScheme.strokeNeutral2Default

    val labelColor = if (isError) colors.errorLabelColor
    else if (!enabled) colors.disabledLabelColor
    else if (isFocused) colors.focusedLabelColor
    else colors.unfocusedLabelColor

    val inputColor = if (isError) colors.errorTextColor
    else if (!enabled) colors.disabledTextColor
    else if (isFocused) colors.focusedTextColor
    else colors.unfocusedTextColor

    Box(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
            .border(
                border = BorderStroke(if (isFocused) 2.dp else 1.dp, borderColor),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        // Label above text field when focused or not empty

        if ((isFocused || value.isNotEmpty()) && label != null) Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = labelColor,
            modifier = Modifier.padding(
                top = 4.dp, start = 16.dp, end = 16.dp
            ) // Adjust padding to position above
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(fontSize = 16.sp, color = inputColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = if (value.isNotEmpty() || isFocused) 10.dp else 0.dp, bottom = 8.dp
                    )
                    .weight(1f)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                        onFocused?.invoke(isFocused)
                    },
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        focusRequester.freeFocus()     // Clear the focus
//                        keyboardActions.onDone
//                    },
//                    onGo = keyboardActions.onGo,
//                    onNext = keyboardActions.onNext,
//                    onPrevious = keyboardActions.onPrevious,
//                    onSearch = keyboardActions.onSearch,
//                    onSend = keyboardActions.onSend
//                ),
                enabled = enabled,
                readOnly = readOnly,
//                textStyle = textStyle,
                singleLine = singleLine,
                maxLines = maxLines,
                minLines = minLines,
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            if (value.isEmpty() && !isFocused && label != null) {
                                BodyMediumText(
                                    modifier = Modifier.padding(bottom = 6.dp),
                                    text = label,
                                    color = labelColor
                                )
                            }
                            innerTextField()
                        }
                    }
                },
            )

            // Trailing Icon
            trailingIcon()
        }
    }
}


@Composable
fun AppSingleTextField(
    modifier: Modifier = Modifier, value: String, label: String, onValueChange: (String) -> Unit
) {
    AppBaseTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = label,
        singleLine = true,
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
    onValueChange: (String) -> Unit,
) {
    AppBaseTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = label,
        singleLine = true,
        onValueChange = { newText ->
            // Ensure that the input consists of only digits
            if (newText.all { it.isDigit() }) {
                onValueChange(newText)
            }
        },
        trailingIcon = @Composable {
            if (value.isNotEmpty()) AppButtonIcon(icon = Icons.Default.Close) { onValueChange("") }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
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
                        AppButtonIcon(icon = IconType.ImageVector(imageVector = Icons.Outlined.Close),
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
