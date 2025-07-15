package com.pmb.calender.utils

import io.github.persiancalendar.calendar.CivilDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date
import java.util.GregorianCalendar
import java.util.TimeZone


const val DEFAULT_IRAN_TIME = false
const val IRAN_TIMEZONE_ID = "Asia/Tehran"

private val isForcedIranTimeEnabled_ = MutableStateFlow(DEFAULT_IRAN_TIME)
val isForcedIranTimeEnabled: StateFlow<Boolean> get() = isForcedIranTimeEnabled_

var weekStartOffset = 0
    private set

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