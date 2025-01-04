package com.pmb.core.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.toCurrency(): String {
    val formatter = NumberFormat.getInstance(Locale.US).apply {
        maximumFractionDigits = 0 // Removes decimal points
    }
    return formatter.format(this)
}