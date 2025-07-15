package com.pmb.calender

import com.pmb.calender.utils.persianCalendarMonthsInPersian
import io.github.persiancalendar.calendar.PersianDate


fun today(): PersianDate = Jdn.today().toPersianDate()

fun daysBetween(from: PersianDate, to: PersianDate): Int = Jdn(to) - Jdn(from)

fun daysFromTodayTo(date: PersianDate): Int = Jdn(date) - Jdn(today())

fun addDays(date: PersianDate, days: Int): PersianDate = (Jdn(date) + days).toPersianDate()
fun subtractDays(date: PersianDate, days: Int): PersianDate =
    (Jdn(date) - days).toPersianDate()

fun isPast(date: PersianDate): Boolean = Jdn(date) < Jdn(today())
fun isFuture(date: PersianDate): Boolean = Jdn(date) > Jdn(today())
fun isToday(date: PersianDate): Boolean = Jdn(date) == Jdn(today())

fun lastDayOfMonth(date: PersianDate): PersianDate =
    subtractDays(date.monthStartOfMonthsDistance(1), 1)

fun PersianDate.monthName(): String =
    persianCalendarMonthsInPersian[this.month - 1]

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
