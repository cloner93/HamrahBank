package com.pmb.core.utils

fun String.isMobile(): MobileValidationResult {
    val mobileRegex = Regex("^(0098|\\+98|09)\\d{9}$")
    val length = when {
        startsWith("0098") -> 14
        startsWith("+98") -> 13
        startsWith("09") -> 11
        else -> 14
    }
    return MobileValidationResult(length = length, isValid = mobileRegex.matches(this))
}

fun String.isIranianNationalId(): Boolean {
    // First, check if the ID is a 10-digit number
    val nationalIdRegex = Regex("^\\d{10}$")
    if (!nationalIdRegex.matches(this)) {
        return false
    }

    // Convert the ID to a list of integers
    val digits = this.map { it.toString().toInt() }

    // Check if all digits are the same (invalid national ID)
    if (digits.distinct().size == 1) {
        return false
    }

    // Calculate the checksum using the first 9 digits
    val checksum = digits[9]
    val sum = (0..8).sumBy { i -> digits[i] * (10 - i) }
    val remainder = sum % 11

    // Valid national ID if the remainder is either equal to the checksum,
    // or the remainder is 10 and the checksum is 0
    return (remainder < 2 && remainder == checksum) || (remainder >= 2 && 11 - remainder == checksum)
}

fun String.isPassword(): PasswordValidationResult {
    val minLen = this.length >= 8
    val lowercase = this.any { it.isLowerCase() }
    val uppercase = this.any { it.isUpperCase() }
    val digit = this.any { it.isDigit() }
    val specialChar = this.any { it in "!@#\$%^&*()_+\\-=\\[\\]{};':\"|,.<>?/" }

    return PasswordValidationResult(
        minLen = minLen,
        lowercase = lowercase,
        uppercase = uppercase,
        digit = digit,
        specialChar = specialChar
    )
}

data class MobileValidationResult(
    val length: Int,
    val isValid: Boolean
)

data class PasswordValidationResult(
    val minLen: Boolean = false,
    val lowercase: Boolean = false,
    val uppercase: Boolean = false,
    val digit: Boolean = false,
    val specialChar: Boolean = false
) {
    val isValid: Boolean
        get() = minLen && lowercase && uppercase && digit && specialChar
}

