package com.pmb.ballon.component.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.pmb.core.utils.isIranianNationalId
import com.pmb.core.utils.isMobile


@Composable
internal fun AppBaseTextField(
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
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }


    Box(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
            .border(
                border = BorderStroke(
                    if (isFocused) 2.dp else 1.dp, if (isFocused) Color.Black else Color.LightGray
                ), shape = RoundedCornerShape(12.dp)
            )
    ) {
        // Label above text field when focused or not empty

        if ((isFocused || value.isNotEmpty()) && label != null) Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
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
                textStyle = TextStyle(fontSize = 16.sp),
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
                                Text(
                                    text = label, // Placeholder text in Persian
                                    fontSize = 16.sp, modifier = Modifier.padding(bottom = 6.dp)
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
