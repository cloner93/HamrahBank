package com.pmb.core.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.toCurrency(): String {
    val formatter = NumberFormat.getInstance(Locale.US).apply {
        maximumFractionDigits = 0 // Removes decimal points
    }
    return formatter.format(this)
}

fun String.toCurrency(): String {
    // separate amount string 3 digits each with comma. like 100000000 to 100,000,000
    return this
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}
