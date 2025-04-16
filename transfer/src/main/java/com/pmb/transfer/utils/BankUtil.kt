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
        return Bank.entries.find { bank ->
            accountNumber.startsWith(bank.accountPrefix)
        }
    }

    fun getBankBySheba(iban: String): Bank? {
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

    fun formatCardNumber(cardNumber: String): String {
        return cardNumber.chunked(4).joinToString("  ")
    }

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
}

