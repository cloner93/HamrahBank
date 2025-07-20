package com.pmb.calender

import io.github.persiancalendar.calendar.PersianDate


fun today(): PersianDate = Jdn.today().toPersianDate()

fun currentMonthPair(): Pair<PersianDate, PersianDate> =
    (firstDayOfMonth(today()) to today())

fun daysBetween(from: PersianDate, to: PersianDate): Int = Jdn(to) - Jdn(from)

fun daysFromTodayTo(date: PersianDate): Int = Jdn(date) - Jdn(today())

fun addDays(date: PersianDate, days: Int): PersianDate = (Jdn(date) + days).toPersianDate()
fun PersianDate.addDay(day: Int) = (Jdn(this) + day).toPersianDate()

fun subtractDays(date: PersianDate, days: Int): PersianDate =
    (Jdn(date) - days).toPersianDate()

fun isPast(date: PersianDate): Boolean = Jdn(date) < Jdn(today())
fun isFuture(date: PersianDate): Boolean = Jdn(date) > Jdn(today())
fun isToday(date: PersianDate): Boolean = Jdn(date) == Jdn(today())

fun lastDayOfMonth(date: PersianDate): PersianDate =
    subtractDays(date.monthStartOfMonthsDistance(1), 1)

fun firstDayOfMonth(date: PersianDate): PersianDate = date.monthStartOfMonthsDistance(0)



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



fun Long.longToString(): Triple<String, String, String>? {
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