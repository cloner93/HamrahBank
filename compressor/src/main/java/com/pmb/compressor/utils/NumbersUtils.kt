package com.pmb.compressor.utils

import kotlin.math.roundToInt

private fun roundEven(value: Int): Int = value + 1 and 1.inv()

fun roundDimension(value: Double): Int =
    roundEven(((value / 16).roundToInt() * 16))
