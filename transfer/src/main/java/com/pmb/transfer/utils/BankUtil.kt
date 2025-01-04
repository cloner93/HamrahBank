package com.pmb.transfer.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.pmb.transfer.domain.Bank

object BankUtil {

    @Composable
    fun getLogo(cardNumber: Long): Painter {
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
}

