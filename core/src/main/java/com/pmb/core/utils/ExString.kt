package com.pmb.core.utils

fun String.isMobile(): MobileValidationResult {
    var validFormat = false
    val length = when {
        startsWith("09") -> {
            validFormat = true
            11
        }

        startsWith("9") -> {
            validFormat = true
            10
        }

        else -> 11
    }
    return MobileValidationResult(length = length, isValid = validFormat && this.length == length)
}

fun String?.validatePhone(): Boolean {
    return (this?.matches(Regex("^\\+?[0-9]{10,13}$"))
        ?: false) && (this?.startsWith("09") == true || this?.startsWith("9") == true)
}

fun String.setMobileValidator(): String {
    val trimmed = this.trim().filter { it.isDigit() }

    return if (trimmed.startsWith("9") && trimmed.length == 10) {
        "0$trimmed"
    } else {
        trimmed
    }
}

fun String.allowOnlyEnglishLettersAndDigits(): Boolean {
    return this.all { it.isDigit() || it in 'a'..'z' || it in 'A'..'Z' }
}

fun String.allowOnlyEnglishLettersDigitsAndSymbols(): Boolean {
    return this.all {
        it.code in 32..126 // ASCII printable characters: includes letters, digits, symbols
    }
}

fun isValidInput(input: String): Boolean {
    val regex = "^[A-Za-z0-9@_-]+$".toRegex()
    return regex.matches(input)
}

fun String.isValidCustomInput(): Boolean {
    if (this.length != 10) return false

    val allDigits = this.all { it.isDigit() }
    if (allDigits) return true

    val firstIsDigit = this[0].isDigit()
    val secondIsEnglishLetter = this[1] in 'a'..'z' || this[1] in 'A'..'Z'
    val remainingAreDigits = this.substring(2).all { it.isDigit() }

    return firstIsDigit && secondIsEnglishLetter && remainingAreDigits
}

fun String.setNationalCodeValidator(): String {
    val trimmed = this.trim().filter { it.isDigit() }

    return if (trimmed.length == 8) {
        "00$trimmed"
    } else if (trimmed.length == 9) {
        "0$trimmed"
    } else {
        trimmed
    }
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
    val sum = (0..8).sumOf { i -> digits[i] * (10 - i) }
    val remainder = sum % 11

    // Valid national ID if the remainder is either equal to the checksum,
    // or the remainder is 10 and the checksum is 0
    return (remainder < 2 && remainder == checksum) || (remainder >= 2 && 11 - remainder == checksum)
}

fun String.isPassword(): PasswordValidationResult = PasswordValidationResult(
    minLen = length >= 10,
    lowercase = contains(Regex("[a-z]")),
    uppercase = contains(Regex("[A-Z]")),
    digit = contains(Regex("[0-9]")),
)

data class MobileValidationResult(
    val length: Int, val isValid: Boolean
)

data class PasswordValidationResult(
    val minLen: Boolean = false,
    val lowercase: Boolean = false,
    val uppercase: Boolean = false,
    val digit: Boolean = false,
) {
    val isValid: Boolean
        get() = minLen && lowercase && uppercase && digit
}

data class UsernameValidationResult(
    val minLen: Boolean = false,
    val maxLen: Boolean = false,
    val startWithLetter: Boolean = false,
    val specialChar: Boolean = false,
    val space: Boolean = false
) {
    val isValid: Boolean
        get() = minLen && maxLen && startWithLetter && specialChar && !space

    companion object {
        fun validate(value: String): UsernameValidationResult = UsernameValidationResult(
            minLen = value.length >= 5,
            maxLen = value.length <= 30,
            startWithLetter = value.startWithEnglishLetter(),
            specialChar = value.isValidChars(),
            space = value.contains(Regex("\\s"))
        )
    }
}

fun String.isValidChars(): Boolean {
    val regex = Regex("^[a-zA-Z0-9@_.-]+$")
    return regex.matches(this)
}

fun String.startWithEnglishLetter(): Boolean {
    val regex = Regex("^[a-zA-Z].*")
    return regex.matches(this)
}

fun String.convertPersianDigitsToEnglish(): String {
    return map { char ->
        when (char) {
            in '۰'..'۹' -> (char.code - '۰'.code + '0'.code).toChar()
            else -> char
        }
    }.joinToString("")
}

fun String.toCurrency(): String {
    // separate amount string 3 digits each with comma. like 100000000 to 100,000,000
    return this.reversed().chunked(3).joinToString(",").reversed()
}
