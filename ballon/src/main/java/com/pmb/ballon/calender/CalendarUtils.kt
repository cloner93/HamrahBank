package com.pmb.ballon.calender

import io.github.persiancalendar.calendar.CivilDate
import java.util.Date
import java.util.GregorianCalendar
import java.util.TimeZone

fun applyWeekStartOffsetToWeekDay(weekDay: Int): Int = (weekDay + 7 - weekStartOffset) % 7

fun GregorianCalendar.toCivilDate(): CivilDate {
    return CivilDate(
        this[GregorianCalendar.YEAR],
        this[GregorianCalendar.MONTH] + 1,
        this[GregorianCalendar.DAY_OF_MONTH]
    )
}

fun Date.toGregorianCalendar(forceLocalTime: Boolean = false): GregorianCalendar {
    val calendar = GregorianCalendar()
    if (!forceLocalTime && isForcedIranTimeEnabled.value)
        calendar.timeZone = TimeZone.getTimeZone(IRAN_TIMEZONE_ID)
    calendar.time = this
    return calendar
}





