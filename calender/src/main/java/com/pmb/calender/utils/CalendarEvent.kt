package com.pmb.calender.utils

import io.github.persiancalendar.calendar.AbstractDate
import io.github.persiancalendar.calendar.PersianDate

sealed class CalendarEvent<T : AbstractDate>(
    val title: String, val isHoliday: Boolean, val date: T
) {

    class EquinoxCalendarEvent(
        title: String, isHoliday: Boolean, date: PersianDate, val remainingMillis: Long
    ) : CalendarEvent<PersianDate>(title, isHoliday, date)


    override fun equals(other: Any?): Boolean {
        return other is CalendarEvent<*>
                && other.title == title && other.isHoliday == isHoliday && other.date == date && (
                if (this is EquinoxCalendarEvent && other is EquinoxCalendarEvent)
                    remainingMillis == other.remainingMillis
                else true)
        // Let's don't get into details of device calendar
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + isHoliday.hashCode()
        result = 31 * result + date.hashCode()
        if (this is EquinoxCalendarEvent) result = 31 * result + remainingMillis.hashCode()
        return result
    }
}
