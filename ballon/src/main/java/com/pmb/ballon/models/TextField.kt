package com.pmb.ballon.models

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.pmb.ballon.ui.theme.AppTheme

object AppTextField {


    /**
     * Creates a [TextFieldColors] that represents the default input text, container, and content
     * colors (including label, placeholder, icons, etc.) used in a [TextField].
     *
     * @param focusedTextColor the color used for the input text of this text field when focused
     * @param unfocusedTextColor the color used for the input text of this text field when not
     *   focused
     * @param disabledTextColor the color used for the input text of this text field when disabled
     * @param errorTextColor the color used for the input text of this text field when in error
     *   state
     * @param focusedContainerColor the container color for this text field when focused
     * @param unfocusedContainerColor the container color for this text field when not focused
     * @param disabledContainerColor the container color for this text field when disabled
     * @param errorContainerColor the container color for this text field when in error state
     * @param cursorColor the cursor color for this text field
     * @param errorCursorColor the cursor color for this text field when in error state
     * @param selectionColors the colors used when the input text of this text field is selected
     * @param focusedIndicatorColor the indicator color for this text field when focused
     * @param unfocusedIndicatorColor the indicator color for this text field when not focused
     * @param disabledIndicatorColor the indicator color for this text field when disabled
     * @param errorIndicatorColor the indicator color for this text field when in error state
     * @param focusedLeadingIconColor the leading icon color for this text field when focused
     * @param unfocusedLeadingIconColor the leading icon color for this text field when not focused
     * @param disabledLeadingIconColor the leading icon color for this text field when disabled
     * @param errorLeadingIconColor the leading icon color for this text field when in error state
     * @param focusedTrailingIconColor the trailing icon color for this text field when focused
     * @param unfocusedTrailingIconColor the trailing icon color for this text field when not
     *   focused
     * @param disabledTrailingIconColor the trailing icon color for this text field when disabled
     * @param errorTrailingIconColor the trailing icon color for this text field when in error state
     * @param focusedLabelColor the label color for this text field when focused
     * @param unfocusedLabelColor the label color for this text field when not focused
     * @param disabledLabelColor the label color for this text field when disabled
     * @param errorLabelColor the label color for this text field when in error state
     * @param focusedPlaceholderColor the placeholder color for this text field when focused
     * @param unfocusedPlaceholderColor the placeholder color for this text field when not focused
     * @param disabledPlaceholderColor the placeholder color for this text field when disabled
     * @param errorPlaceholderColor the placeholder color for this text field when in error state
     * @param focusedSupportingTextColor the supporting text color for this text field when focused
     * @param unfocusedSupportingTextColor the supporting text color for this text field when not
     *   focused
     * @param disabledSupportingTextColor the supporting text color for this text field when
     *   disabled
     * @param errorSupportingTextColor the supporting text color for this text field when in error
     *   state
     * @param focusedPrefixColor the prefix color for this text field when focused
     * @param unfocusedPrefixColor the prefix color for this text field when not focused
     * @param disabledPrefixColor the prefix color for this text field when disabled
     * @param errorPrefixColor the prefix color for this text field when in error state
     * @param focusedSuffixColor the suffix color for this text field when focused
     * @param unfocusedSuffixColor the suffix color for this text field when not focused
     * @param disabledSuffixColor the suffix color for this text field when disabled
     * @param errorSuffixColor the suffix color for this text field when in error state
     */


    @Composable
    fun colors(
        focusedTextColor: Color = Color.Unspecified,
        unfocusedTextColor: Color = Color.Unspecified,
        disabledTextColor: Color = Color.Unspecified,
        errorTextColor: Color = Color.Unspecified,
        focusedContainerColor: Color = Color.Unspecified,
        unfocusedContainerColor: Color = Color.Unspecified,
        disabledContainerColor: Color = Color.Unspecified,
        errorContainerColor: Color = Color.Unspecified,
        cursorColor: Color = Color.Unspecified,
        errorCursorColor: Color = Color.Unspecified,
        selectionColors: TextSelectionColors = TextSelectionColors(
            handleColor = Color.Unspecified,
            backgroundColor = Color.Unspecified
        ),
        focusedIndicatorColor: Color = Color.Unspecified,
        unfocusedIndicatorColor: Color = Color.Unspecified,
        disabledIndicatorColor: Color = Color.Unspecified,
        errorIndicatorColor: Color = Color.Unspecified,
        focusedLeadingIconColor: Color = Color.Unspecified,
        unfocusedLeadingIconColor: Color = Color.Unspecified,
        disabledLeadingIconColor: Color = Color.Unspecified,
        errorLeadingIconColor: Color = Color.Unspecified,
        focusedTrailingIconColor: Color = Color.Unspecified,
        unfocusedTrailingIconColor: Color = Color.Unspecified,
        disabledTrailingIconColor: Color = Color.Unspecified,
        errorTrailingIconColor: Color = Color.Unspecified,
        focusedLabelColor: Color = Color.Unspecified,
        unfocusedLabelColor: Color = Color.Unspecified,
        disabledLabelColor: Color = Color.Unspecified,
        errorLabelColor: Color = Color.Unspecified,
        focusedPlaceholderColor: Color = Color.Unspecified,
        unfocusedPlaceholderColor: Color = Color.Unspecified,
        disabledPlaceholderColor: Color = Color.Unspecified,
        errorPlaceholderColor: Color = Color.Unspecified,
        focusedSupportingTextColor: Color = Color.Unspecified,
        unfocusedSupportingTextColor: Color = Color.Unspecified,
        disabledSupportingTextColor: Color = Color.Unspecified,
        errorSupportingTextColor: Color = Color.Unspecified,
        focusedPrefixColor: Color = Color.Unspecified,
        unfocusedPrefixColor: Color = Color.Unspecified,
        disabledPrefixColor: Color = Color.Unspecified,
        errorPrefixColor: Color = Color.Unspecified,
        focusedSuffixColor: Color = Color.Unspecified,
        unfocusedSuffixColor: Color = Color.Unspecified,
        disabledSuffixColor: Color = Color.Unspecified,
        errorSuffixColor: Color = Color.Unspecified,
    ): TextFieldColors =
        TextFieldColors(
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            disabledTextColor = disabledTextColor,
            errorTextColor = errorTextColor,
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            disabledContainerColor = disabledContainerColor,
            errorContainerColor = errorContainerColor,
            cursorColor = cursorColor,
            errorCursorColor = errorCursorColor,
            textSelectionColors = selectionColors,
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            disabledIndicatorColor = disabledIndicatorColor,
            errorIndicatorColor = errorIndicatorColor,
            focusedLeadingIconColor = focusedLeadingIconColor,
            unfocusedLeadingIconColor = unfocusedLeadingIconColor,
            disabledLeadingIconColor = disabledLeadingIconColor,
            errorLeadingIconColor = errorLeadingIconColor,
            focusedTrailingIconColor = focusedTrailingIconColor,
            unfocusedTrailingIconColor = unfocusedTrailingIconColor,
            disabledTrailingIconColor = disabledTrailingIconColor,
            errorTrailingIconColor = errorTrailingIconColor,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            disabledLabelColor = disabledLabelColor,
            errorLabelColor = errorLabelColor,
            focusedPlaceholderColor = focusedPlaceholderColor,
            unfocusedPlaceholderColor = unfocusedPlaceholderColor,
            disabledPlaceholderColor = disabledPlaceholderColor,
            errorPlaceholderColor = errorPlaceholderColor,
            focusedSupportingTextColor = focusedSupportingTextColor,
            unfocusedSupportingTextColor = unfocusedSupportingTextColor,
            disabledSupportingTextColor = disabledSupportingTextColor,
            errorSupportingTextColor = errorSupportingTextColor,
            focusedPrefixColor = focusedPrefixColor,
            unfocusedPrefixColor = unfocusedPrefixColor,
            disabledPrefixColor = disabledPrefixColor,
            errorPrefixColor = errorPrefixColor,
            focusedSuffixColor = focusedSuffixColor,
            unfocusedSuffixColor = unfocusedSuffixColor,
            disabledSuffixColor = disabledSuffixColor,
            errorSuffixColor = errorSuffixColor,
        )

    @Composable
    fun defaultColors() = colors(
        focusedTextColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        unfocusedTextColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        disabledTextColor = AppTheme.colorScheme.onForegroundNeutralDisabled,
        errorTextColor = AppTheme.colorScheme.onBackgroundErrorDefault,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = AppTheme.colorScheme.background2Neutral,
        errorContainerColor = Color.Transparent,
//        cursorColor =,
//        errorCursorColor =,
//        selectionColors =,
        focusedIndicatorColor = AppTheme.colorScheme.strokeNeutral2Active,
        unfocusedIndicatorColor = AppTheme.colorScheme.strokeNeutral1Default,
        disabledIndicatorColor = AppTheme.colorScheme.strokeNeutral1Default,
        errorIndicatorColor = AppTheme.colorScheme.strokeNeutral2Error,
        focusedLeadingIconColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        unfocusedLeadingIconColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        disabledLeadingIconColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        errorLeadingIconColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        focusedTrailingIconColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        unfocusedTrailingIconColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        disabledTrailingIconColor = AppTheme.colorScheme.onForegroundNeutralDisabled,
        errorTrailingIconColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        focusedLabelColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        unfocusedLabelColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        disabledLabelColor = AppTheme.colorScheme.onForegroundNeutralDisabled,
        errorLabelColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        focusedPlaceholderColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        unfocusedPlaceholderColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
        disabledPlaceholderColor = AppTheme.colorScheme.onForegroundNeutralDisabled,
        errorPlaceholderColor = AppTheme.colorScheme.onBackgroundNeutralDefault,
//        focusedSupportingTextColor =,
//        unfocusedSupportingTextColor =,
//        disabledSupportingTextColor =,
//        errorSupportingTextColor =,
        focusedPrefixColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
        unfocusedPrefixColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
        disabledPrefixColor = AppTheme.colorScheme.onForegroundNeutralDisabled,
        errorPrefixColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
        focusedSuffixColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
        unfocusedSuffixColor = AppTheme.colorScheme.onBackgroundNeutralSubdued,
        disabledSuffixColor = AppTheme.colorScheme.onForegroundNeutralDisabled,
        errorSuffixColor = AppTheme.colorScheme.onBackgroundNeutralSubdued
    )

}