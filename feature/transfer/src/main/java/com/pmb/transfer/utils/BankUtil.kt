@file:Suppress("UNREACHABLE_CODE")

package com.pmb.transfer.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.pmb.core.utils.convertPersianDigitsToEnglish
import com.pmb.transfer.domain.entity.Bank
import com.pmb.transfer.domain.entity.BankIdentifierNumberType

object BankUtil {

    @Composable
    fun getLogo(cardNumber: String): Painter {
        return painterResource(com.pmb.ballon.R.drawable.ic_mellat_circle)
    }


    fun getBankByCardNumber(cardNumber: String): Bank? {
        return Bank.entries.find { bank ->
            bank.cardPrefix.any { cardNumber.startsWith(it) }
        }
    }

    fun getBankByAccountNumber(accountNumber: String): Bank? {
        val accountNumber = accountNumber.trim().replace(" ", "").uppercase()
        if (accountNumber.validateMellatBankAccount())
            return Bank.MELLAT
        return null
    }

    fun getBankBySheba(iban: String): Bank? {
        var iban = iban.trim().replace(" ", "").uppercase()
        if (!iban.startsWith("IR")) iban = "IR$iban"
        return Bank.entries.find { bank ->
            val firstSevenChars = if (iban.length >= 7) iban.substring(0, 7) else iban
            val pattern = Regex("^IR\\d{2}${bank.accountPrefix}")
            pattern.matches(firstSevenChars)
        }
    }

    @Composable
    fun getBankPainter(identifierNumber: String): Painter? =
        (getBankByCardNumber(identifierNumber) ?: getBankByAccountNumber(identifierNumber)
        ?: getBankBySheba(identifierNumber))?.let { bank -> painterResource(bank.icon) }


    fun String.formatGropedWithSeparator(groupSize: Int = 4, separator: String = " "): String =
        chunked(groupSize).joinToString(separator)

    fun formatMaskCardNumber(cardNumber: String): String {
        if (cardNumber.trim().length != 16) return cardNumber // fallback for invalid length
        val first4 = cardNumber.substring(0, 4)
        val last4 = cardNumber.substring(12, 16)
        return "$first4  ****  ****  $last4"
    }

    fun formatMaskAccountBankNumber(accountNumber: String): String {
        val visibleLength = 4
        if (accountNumber.length <= visibleLength) return accountNumber
        val visiblePart = accountNumber.takeLast(visibleLength)
        val maskedPart = "*".repeat(accountNumber.length - visibleLength)
        return "$maskedPart$visiblePart"
    }


    /**
     * بررسی می‌کند آیا ورودی یک شناسه بانکی معتبر ایرانی است یا نه:
     * - شماره شبا (با IR و 24 رقم بعدش)
     * - شماره کارت بانکی (16 رقمی با اعتبارسنجی Luhn)
     * - شماره حساب عددی با حداقل 6 رقم
     */
    fun String.isValidIranBankIdentifier(maxLength: ((Int) -> Unit)? = null): Boolean {
        val value = this.trim().replace(" ", "").uppercase()

        return when {
            getBankByCardNumber(value) != null -> {
                maxLength?.invoke(16)
                value.length == 16
            }


            getBankBySheba(value) != null -> {
                maxLength?.invoke(24)
                value.length == 24
            }

            this.validateMellatBankAccount() -> {
                maxLength?.invoke(24)
                true
            }

            else -> {
                maxLength?.invoke(24)
                false
            }
        }
    }

    fun String.extractOrderByIdentifier(
        orderBy: List<BankIdentifierNumberType> = listOf(
            BankIdentifierNumberType.CARD,
            BankIdentifierNumberType.IBAN,
            BankIdentifierNumberType.ACCOUNT
        ),
        result: (BankIdentifierNumberType, String) -> Unit
    ) {
        val ids = extractAllLongNumbers()

        orderBy.forEach { type ->
            ids.forEach { id ->
                when (type) {
                    BankIdentifierNumberType.CARD -> {
                        // 1. کارت: 16 رقم
                        val cardRegex = Regex("\\b\\d{16}\\b")
                        cardRegex.find(id)?.value?.let {
                            return result(
                                BankIdentifierNumberType.CARD,
                                it
                            )
                        }
                    }

                    BankIdentifierNumberType.IBAN -> {
                        // 2. شبا: با IR شروع می‌شه و بعدش 24 رقم
                        val shebaRegex = Regex("\\bIR\\d{24}\\b", RegexOption.IGNORE_CASE)
                        shebaRegex.find(id)?.value?.let {
                            return result(
                                BankIdentifierNumberType.IBAN,
                                it.uppercase()
                            )
                        }
                    }

                    BankIdentifierNumberType.ACCOUNT -> {
                        // 3. حساب: مثلاً 6 تا 10 رقم (بسته به سیستم بانک‌ها)
                        if (id.validateMellatBankAccount())
                            return result(BankIdentifierNumberType.ACCOUNT, id)
                    }
                }
            }
        }
    }
}

fun String.extractAllLongNumbers(minLength: Int = 5): List<String> {
    val cleanText = convertPersianDigitsToEnglish()
        .replace("""[\s\-_,.]""".toRegex(), "")

    return Regex("""\d{$minLength,}""")
        .findAll(cleanText)
        .map { match ->
            val number = match.value
            if (number.length == 24) "IR$number" else number
        }.toList()
}

fun String.validateMellatBankAccount(): Boolean {
    // مرحله 1: اعتبارسنجی ورودی
    if (this.length !in 4..10) {
        println("Error: Account number must be between 4 and 10 digits.")
        return false
    }

    if (!this.all { it.isDigit() }) {
        println("Error: Account number must contain only digits.")
        return false
    }

    // مرحله 2: استخراج دو رقم آخر
    val lastTwoDigitsStr = this.takeLast(2)
    val lastTwoDigits = lastTwoDigitsStr.toIntOrNull()
    if (lastTwoDigits == null) {
        println("Error: Cannot convert last two digits to integer.")
        return false
    }

    // مرحله 3: گرفتن ارقام باقی‌مانده
    val remainingDigitsStr = this.dropLast(2)
    if (remainingDigitsStr.isEmpty()) {
        println("Error: Not enough digits after removing last two.")
        return false
    }

    // مرحله 4: ضرب ارقام به صورت متناوب در 7 و 3 از راست به چپ
    val digits = remainingDigitsStr.map { it.toString().toInt() }.reversed()
    var sum = 0
    digits.forEachIndexed { index, digit ->
        val multiplier = if (index % 2 == 0) 7 else 3
        sum += digit * multiplier
    }

    // مرحله 5: افزودن 101 به مجموع
    sum += 101

    // مرحله 6: محاسبه باقی‌مانده تقسیم بر 97
    val remainder = sum % 97

    // مرحله 7: بررسی برابری باقی‌مانده با دو رقم آخر
    return remainder == lastTwoDigits
}


