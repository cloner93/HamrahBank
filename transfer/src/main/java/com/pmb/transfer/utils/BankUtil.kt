package com.pmb.transfer.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.pmb.transfer.domain.entity.Bank

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
        return Bank.entries.find { bank ->
            accountNumber.startsWith(bank.accountPrefix)
        }
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

            getBankByAccountNumber(value) != null -> {
                maxLength?.invoke(20)
                value.length in 6..20
            }

            getBankBySheba(value) != null -> {
                maxLength?.invoke(24)
                value.length == 24
            }

            else -> {
                maxLength?.invoke(24)
                false
            }
        }
    }

    /**
     *  عددهایی بین 13 تا 26 رقم (مناسب برای کارت، شبا، حساب)
     */
    fun String.extractAllNumbers(minLength: Int = 6): List<String> {
        val regex = Regex("\\d{$minLength,26}")
        return regex.findAll(this).map { it.value }.toList()
    }
}

