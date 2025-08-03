package com.pmb.calender

import com.pmb.calender.utils.Calendar
import com.pmb.calender.utils.getDayOfWeekName
import com.pmb.calender.utils.persianCalendarDayOfWeekInPersian
import com.pmb.calender.utils.persianCalendarMonthsInPersian
import com.pmb.calender.utils.toCivilDate
import com.pmb.calender.utils.toGregorianCalendar
import io.github.persiancalendar.calendar.PersianDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun today(): PersianDate = Jdn.today().toPersianDate()

fun currentMonthPair(): Pair<PersianDate, PersianDate> =
    (firstDayOfMonth(today()) to today())

fun daysBetween(from: PersianDate, to: PersianDate): Int = Jdn(to) - Jdn(from)

fun daysFromTodayTo(date: PersianDate): Int = Jdn(date) - Jdn(today())

fun addDays(date: PersianDate, days: Int): PersianDate = (Jdn(date) + days).toPersianDate()
fun PersianDate.addDay(day: Int) = (Jdn(this) + day).toPersianDate()

fun PersianDate.subtractDays(days: Int): PersianDate =
    (Jdn(this) - days).toPersianDate()

fun isPast(date: PersianDate): Boolean = Jdn(date) < Jdn(today())
fun isFuture(date: PersianDate): Boolean = Jdn(date) > Jdn(today())
fun isToday(date: PersianDate): Boolean = Jdn(date) == Jdn(today())

fun lastDayOfMonth(date: PersianDate): PersianDate =
    date.monthStartOfMonthsDistance(1).subtractDays(1)

fun firstDayOfMonth(date: PersianDate): PersianDate = date.monthStartOfMonthsDistance(0)

fun PersianDate.monthName(): String =
    persianCalendarMonthsInPersian[this.month - 1]

fun PersianDate.dayOfWeekName(): String =
    persianCalendarDayOfWeekInPersian[this.dayOfMonth - 1]

fun Date.formatInReceipt(): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val currentTime = sdf.format(this)

    val calendar = java.util.Calendar.getInstance()
    calendar.time = this

    val dayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK) // 1=Sunday, 7=Saturday

    Jdn(this.toGregorianCalendar().toCivilDate()).toPersianDate().let { persianDate ->
        return "${getDayOfWeekName(dayOfWeek)} ${persianDate.dayOfMonth} ${persianDate.monthName()} ${persianDate.year}، ساعت $currentTime"
    }
}

fun Date.formatSimple(): String {
    Jdn(this.toGregorianCalendar().toCivilDate()).toPersianDate().let { persianDate ->
        return "${persianDate.dayOfMonth} ${persianDate.monthName()} ${persianDate.year}"
    }
}

fun Int.monthName(): String =
    persianCalendarMonthsInPersian[this - 1]

fun PersianDate.toLong(): Long {
    val stringDate = this.year.toString() +
            this.month.toString().padStart(2, '0') +
            this.dayOfMonth.toString().padStart(2, '0')

    return stringDate.toLong()
}

fun generateShiftedMonthList(
    currentDate: PersianDate = today()
): List<Pair<PersianDate, PersianDate>> {
    val list = mutableListOf<Pair<PersianDate, PersianDate>>()

    for (i in 10 downTo -1) {
        val startDate = currentDate.monthStartOfMonthsDistance(-i)  // 1404 04 01
        val endDate = lastDayOfMonth(startDate)                                     // 1404 04 31

        list.add(startDate to endDate)
    }
    return list
}

fun formatPersianDateForDisplay(date: String, time: String?): String {

    val year = date.substring(0, 4).toInt()
    val month = date.substring(4, 6).toInt()
    val day = date.substring(6, 8).toInt()

    val transactionDate = Jdn(Calendar.SHAMSI, year, month, day).toPersianDate()

    val now = today()
    val yesterday = today().apply { addDay(-1) }

    var timeStr = ""

    time?.let {
        val hour = time.substring(0, 2).toInt()
        val minute = time.substring(2, 4).toInt()
        val second = time.substring(4, 6).toInt()

        timeStr = "ساعت $hour:$minute:$second"
    }


    return when {
        transactionDate.year == now.year && transactionDate.month == now.month && transactionDate.dayOfMonth == now.dayOfMonth -> {
            "امروز $timeStr"
        }

        transactionDate.year == yesterday.year && transactionDate.month == yesterday.month && transactionDate.dayOfMonth == yesterday.dayOfMonth -> {
            "دیروز $timeStr"
        }

        transactionDate.year == now.year -> {
            val monthName = transactionDate.monthName()
            "${transactionDate.dayOfMonth} $monthName $timeStr"
        }

        else -> {
            val monthName = transactionDate.monthName()
            "${transactionDate.dayOfMonth} $monthName ${transactionDate.year} $timeStr"
        }
    }
}

// 14040404 -> 1404 tir 03
fun String?.toPersianDateString(): String {
    if (this == null || this.length != 8) return ""

    val year = this.substring(0, 4)
    val month = this.substring(4, 6).toInt().monthName()
    val day = this.substring(6, 8)

    return " $day $month $year"
}

fun Long.longToString(): Triple<String, String, String>? {
    if (this == 0L) return null

    val dateStr = this.toString().padStart(8, '0')
    return if (dateStr.length == 8) {
        val year = dateStr.substring(0, 4)
        val month = dateStr.substring(4, 6)
        val day = dateStr.substring(6, 8)
        Triple(year, month, day)
    } else {
        null
    }
}