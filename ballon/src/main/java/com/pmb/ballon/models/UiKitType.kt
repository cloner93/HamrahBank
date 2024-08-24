package com.pmb.ballon.models

import androidx.compose.ui.unit.Dp


sealed class Size {
    data object DEFAULT : Size()
    data class FIX(val all: Dp) : Size()
    data class Rectangle(val width: Dp, val height: Dp) : Size()
}
