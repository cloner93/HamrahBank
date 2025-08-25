package com.pmb.calender.utils

import androidx.annotation.StringRes
import com.pmb.calender.Jdn
import com.pmb.calender.R
import io.github.persiancalendar.calendar.AbstractDate
import io.github.persiancalendar.calendar.CivilDate
import io.github.persiancalendar.calendar.IslamicDate
import io.github.persiancalendar.calendar.PersianDate

enum class Calendar(
    @StringRes val title: Int, @StringRes val shortTitle: Int, val preferredDigits: CharArray
) {
    // So vital, don't ever change names of these
    SHAMSI(
        R.string.shamsi_calendar, R.string.shamsi_calendar_short, PERSIAN_DIGITS
    ),
    ISLAMIC(
        R.string.islamic_calendar, R.string.islamic_calendar_short, ARABIC_INDIC_DIGITS
    ),
    GREGORIAN(
        R.string.gregorian_calendar, R.string.gregorian_calendar_short, ARABIC_DIGITS
    );

    fun createDate(year: Int, month: Int, day: Int): AbstractDate = when (this) {
        ISLAMIC -> IslamicDate(year, month, day)
        GREGORIAN -> CivilDate(year, month, day)
        SHAMSI -> PersianDate(year, month, day)
    }

    // "The values are numbered following the ISO-8601 standard, from 1 (Monday) to 7 (Sunday)."
    fun getNthWeekDayOfMonth(year: Int, month: Int, weekDay: Int, nth: Int): Int {
        val appWeekDay = (weekDay % 7) + 1
        val monthStartWeekDay = Jdn(this, year, month, 1).weekDay
        return appWeekDay - monthStartWeekDay + nth * 7 - if (monthStartWeekDay < appWeekDay) 7 else 0
    }

    // "The values are numbered following the ISO-8601 standard, from 1 (Monday) to 7 (Sunday)."
    fun getLastWeekDayOfMonth(year: Int, month: Int, weekDay: Int): Int {
        val appWeekDay = (weekDay % 7) + 1
        val monthLength = getMonthLength(year, month)
        return monthLength - (Jdn(this, year, month, monthLength) - appWeekDay + 1).weekDay
    }

    fun getYearMonths(year: Int): Int =
        (Jdn(this, year + 1, 1, 1) - 1).on(this).month

    fun getMonthLength(year: Int, month: Int): Int =
        (Jdn(getMonthStartFromMonthsDistance(year, month, 1)) - 1).on(this).dayOfMonth

    private fun getMonthStartFromMonthsDistance(
        baseYear: Int, baseMonth: Int, monthsDistance: Int
    ): AbstractDate = when (this) {
        ISLAMIC -> IslamicDate(baseYear, baseMonth, 1).monthStartOfMonthsDistance(monthsDistance)
        GREGORIAN -> CivilDate(baseYear, baseMonth, 1).monthStartOfMonthsDistance(monthsDistance)
        SHAMSI -> PersianDate(baseYear, baseMonth, 1).monthStartOfMonthsDistance(monthsDistance)
    }

    fun getMonthsDistance(baseJdn: Jdn, toJdn: Jdn): Int = when (this) {
        ISLAMIC -> baseJdn.toIslamicDate().monthsDistanceTo(toJdn.toIslamicDate())
        GREGORIAN -> baseJdn.toCivilDate().monthsDistanceTo(toJdn.toCivilDate())
        SHAMSI -> baseJdn.toPersianDate().monthsDistanceTo(toJdn.toPersianDate())
    }

    fun getMonthStartFromMonthsDistance(baseJdn: Jdn, monthsDistance: Int): AbstractDate {
        val date = baseJdn on this
        return getMonthStartFromMonthsDistance(date.year, date.month, monthsDistance)
    }
}

val PERSIAN_DIGITS = charArrayOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')
val ARABIC_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
val ARABIC_INDIC_DIGITS = charArrayOf('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩')

val persianCalendarMonthsInPersian = listOf(
    "فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد",
    "شهریور", "مهر", "آبان", "آذر", "دی",
    "بهمن", "اسفند"
)
val persianCalendarMonthsInDariOrPersianOldEra = listOf(
    "حمل", "ثور", "جوزا", "سرطان", "اسد", "سنبله",
    "میزان", "عقرب", "قوس", "جدی", "دلو", "حوت"
)

val persianCalendarDayOfWeekInPersian = listOf(
    "شنبه", "یک‌شنبه", "دوشنبه", "سه‌شنبه", "چهارشنبه", "پنجشنبه", "جمعه"
)

fun getDayOfWeekName(dayOfWeek: Int): String = when (dayOfWeek) {
    1    -> "یک‌شنبه"
    2    -> "دوشنبه"
    3    -> "سه‌شنبه"
    4    -> "چهارشنبه"
    5    -> "پنجشنبه"
    6    -> "جمعه"
    7    -> "شنبه"
    else -> "نامعلوم"
}