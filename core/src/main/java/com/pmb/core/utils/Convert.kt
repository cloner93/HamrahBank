package com.pmb.core.utils

import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Convert {
    fun numberToWords(number: Double): String {
        if (number == 0.0) return "صفر"

        val units = arrayOf(
            "", "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه"
        )
        val teens = arrayOf(
            "ده", "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده"
        )
        val tens = arrayOf(
            "", "", "بیست", "سی", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"
        )
        val hundreds = arrayOf(
            "", "صد", "دویست", "سیصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد"
        )
        val thousands = arrayOf(
            "",
            "هزار",
            "میلیون",
            "میلیارد",
            "بیلیون",
            "بیلیارد",
            "تریلیون",
            "تریلیارد",
            "کوآدریلیون",
            "کوآدریلیارد"
        )

        var num = number.toLong().toBigInteger().divide(BigInteger.TEN) // تبدیل ریال به تومان
        var words = ""
        var thousandCounter = 0

        while (num > BigInteger.ZERO) {
            val part = num.mod(BigInteger("1000")).toInt()
            if (part != 0) {
                val partWords = convertThreeDigits(part)
                val thousandWord =
                    if (thousandCounter < thousands.size) thousands[thousandCounter] else ""
                words = "$partWords $thousandWord ${if (words.isNotEmpty()) "و" else ""} $words"
            }
            num = num.divide(BigInteger("1000"))
            thousandCounter++
        }

        return words.trim()
    }

    private fun convertThreeDigits(number: Int): String {
        val units = arrayOf(
            "", "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه"
        )
        val teens = arrayOf(
            "ده", "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده"
        )
        val tens = arrayOf(
            "", "", "بیست", "سی", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"
        )
        val hundreds = arrayOf(
            "", "صد", "دویست", "سیصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد"
        )

        val hundred = number / 100
        val remainder = number % 100
        val ten = remainder / 10
        val unit = remainder % 10

        val words = mutableListOf<String>()
        if (hundred > 0) words.add(hundreds[hundred])
        if (remainder in 10..19) {
            words.add(teens[remainder - 10])
        } else {
            if (ten > 0) words.add(tens[ten])
            if (unit > 0) words.add(units[unit])
        }

        return words.joinToString(" و ")
    }


    fun timestampToPersianDate(timestamp: Long): String {
        val persianLocale = Locale("fa", "IR") // Persian locale
        // Convert timestamp to Date object
        val date = Date(timestamp)
        // Create a SimpleDateFormat with Persian locale
        val dateFormat = SimpleDateFormat("EEEE dd MMMM yyyy، ساعت HH:mm", persianLocale)
        // Format the date and return it
        return dateFormat.format(date)
    }
}