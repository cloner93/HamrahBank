package com.pmb.ballon.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp


sealed class Size {
    data object DEFAULT : Size()
    data class FIX(val all: Dp) : Size()
    data class Rectangle(val width: Dp, val height: Dp) : Size()
}

sealed class TrailingType {
    data object NONE : TrailingType()
    data object CLEAR : TrailingType()
    data object SHOW_PASSWORD : TrailingType()
    data class CUSTOM(val allwayShow: Boolean = true, val icon: @Composable () -> Unit) :
        TrailingType()
}
