package com.pmb.ballon.models

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.pmb.ballon.ui.theme.AppTheme

object AppButton {

    @Composable
    fun buttonColors(
        containerColor: Color = AppTheme.colorScheme.foregroundNeutralDefault, // Background color
        contentColor: Color = AppTheme.colorScheme.onForegroundNeutralDefault, // Text color
        disabledContainerColor: Color = AppTheme.colorScheme.foregroundNeutralDisabled, // Disabled background color
        disabledContentColor: Color = AppTheme.colorScheme.onForegroundNeutralDisabled // Disabled text color
    ): ButtonColors =
        ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )

    @Composable
    fun buttonRedColors(
        containerColor: Color = AppTheme.colorScheme.foregroundErrorDefault, // Background color
        contentColor: Color = AppTheme.colorScheme.onForegroundNeutralDefault, // Text color
        disabledContainerColor: Color = AppTheme.colorScheme.foregroundNeutralDisabled, // Disabled background color
        disabledContentColor: Color = AppTheme.colorScheme.onForegroundNeutralDisabled // Disabled text color
    ): ButtonColors =
        ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )

    @Composable
    fun outlinedButtonColors(
        containerColor: Color = Color.Transparent, // Background color
        contentColor: Color = AppTheme.colorScheme.onBackgroundTintNeutralDefault, // Text color
        disabledContainerColor: Color = Color.Transparent, // Disabled background color
        disabledContentColor: Color = AppTheme.colorScheme.onForegroundNeutralDisabled // Disabled text color
    ): ButtonColors =
        ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )

    @Composable
    fun textButtonColors(
        containerColor: Color = Color.Transparent, // Background color
        contentColor: Color = AppTheme.colorScheme.onBackgroundTintNeutralDefault, // Text color
        disabledContainerColor: Color = Color.Transparent, // Disabled background color
        disabledContentColor: Color = AppTheme.colorScheme.onForegroundNeutralDisabled // Disabled text color
    ): ButtonColors =
        ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )

    @Composable
    fun textButtonRedColors(
        containerColor: Color = Color.Transparent, // Background color
        contentColor: Color = AppTheme.colorScheme.onBackgroundNeutralCTA, // Text color
        disabledContainerColor: Color = Color.Transparent, // Disabled background color
        disabledContentColor: Color = AppTheme.colorScheme.onForegroundNeutralDisabled // Disabled text color
    ): ButtonColors =
        ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )

    @Composable
    fun textButtonBlueColors(
        containerColor: Color = Color.Transparent, // Background color
        contentColor: Color = AppTheme.colorScheme.iconColor, // Text color
        disabledContainerColor: Color = Color.Transparent, // Disabled background color
        disabledContentColor: Color = AppTheme.colorScheme.onForegroundNeutralDisabled // Disabled text color
    ): ButtonColors =
        ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )
}