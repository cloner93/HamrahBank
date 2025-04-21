package com.pmb.ballon.calender

import android.content.Context
import androidx.collection.LongSet
import androidx.collection.emptyLongSet
import io.github.persiancalendar.calendar.AbstractDate
import io.github.persiancalendar.calendar.CivilDate
import io.github.persiancalendar.calendar.IslamicDate
import io.github.persiancalendar.calendar.PersianDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Using global variable isn't really the best idea.
// Somehow it's a legacy thing for this now aged project.
// We have limited most of global variable to this package and
// are avoiding storing complicated things on it.

private val monthNameEmptyList = List(12) { "" }
var persianMonths = monthNameEmptyList
    private set
var oldEraPersianMonths = monthNameEmptyList
    private set
var islamicMonths = monthNameEmptyList
    private set
var gregorianMonths = monthNameEmptyList
    private set
var nepaliMonths = monthNameEmptyList
    private set
private val weekDaysEmptyList = List(7) { "" }
var weekDays = weekDaysEmptyList
    private set
var weekDaysInitials = weekDaysEmptyList
    private set


private val isForcedIranTimeEnabled_ = MutableStateFlow(DEFAULT_IRAN_TIME)
val isForcedIranTimeEnabled: StateFlow<Boolean> get() = isForcedIranTimeEnabled_

private val isNotifyDateOnLockScreen_ = MutableStateFlow(DEFAULT_NOTIFY_DATE_LOCK_SCREEN)
val isNotifyDateOnLockScreen: StateFlow<Boolean> get() = isNotifyDateOnLockScreen_

private val prefersWidgetsDynamicColors_ = MutableStateFlow(false)
val prefersWidgetsDynamicColorsFlow: StateFlow<Boolean> get() = prefersWidgetsDynamicColors_

var isWidgetClock = DEFAULT_WIDGET_CLOCK
    private set

private val isNotifyDate_ = MutableStateFlow(DEFAULT_NOTIFY_DATE)
val isNotifyDate: StateFlow<Boolean> get() = isNotifyDate_

private val notificationAthan_ = MutableStateFlow(isNotifyDate.value)
val notificationAthan: StateFlow<Boolean> get() = notificationAthan_
private val athanVibration_ = MutableStateFlow(DEFAULT_ATHAN_VIBRATION)
val athanVibration: StateFlow<Boolean> get() = athanVibration_
private val ascendingAthan_ = MutableStateFlow(DEFAULT_ASCENDING_ATHAN_VOLUME)
val ascendingAthan: StateFlow<Boolean> get() = ascendingAthan_


private val athanSoundName_ = MutableStateFlow<String?>(null)
val athanSoundName: StateFlow<String?> get() = athanSoundName_

private val language_ = MutableStateFlow(Language.FA)
val language: StateFlow<Language> get() = language_


private val isGradient_ = MutableStateFlow(DEFAULT_THEME_GRADIENT)
val isGradient: StateFlow<Boolean> get() = isGradient_


private val isRedHolidays_ = MutableStateFlow(DEFAULT_RED_HOLIDAYS)
val isRedHolidays: StateFlow<Boolean> get() = isRedHolidays_


private val isVazirEnabled_ = MutableStateFlow(DEFAULT_VAZIR_ENABLED)
val isVazirEnabled: StateFlow<Boolean> get() = isVazirEnabled_

private var alternativeGregorianMonths = false
private var alternativePersianMonthsInAzeri = false


private val cityName_ = MutableStateFlow<String?>(null)
val cityName: StateFlow<String?> get() = cityName_

private val widgetTransparency_ = MutableStateFlow(.0f)
val widgetTransparency: StateFlow<Float> get() = widgetTransparency_

var enabledCalendars = listOf(Calendar.SHAMSI, Calendar.GREGORIAN, Calendar.ISLAMIC)
    private set
val mainCalendar inline get() = enabledCalendars.getOrNull(0) ?: Calendar.SHAMSI

val secondaryCalendar
    get() = if (secondaryCalendarEnabled) enabledCalendars.getOrNull(1) else null

var isCenterAlignWidgets = true
    private set
var weekStartOffset = 0
    private set
var weekEnds = BooleanArray(7)
    private set

private val isShowWeekOfYearEnabled_ = MutableStateFlow(false)
val isShowWeekOfYearEnabled: StateFlow<Boolean> get() = isShowWeekOfYearEnabled_

private val dreamNoise_ = MutableStateFlow(DEFAULT_DREAM_NOISE)
val dreamNoise: StateFlow<Boolean> get() = dreamNoise_

private val wallpaperDark_ = MutableStateFlow(DEFAULT_WALLPAPER_DARK)
val wallpaperDark: StateFlow<Boolean> get() = wallpaperDark_

private val wallpaperAutomatic_ = MutableStateFlow(DEFAULT_WALLPAPER_AUTOMATIC)
val wallpaperAutomatic: StateFlow<Boolean> get() = wallpaperAutomatic_

private val isShowDeviceCalendarEvents_ = MutableStateFlow(false)
val isShowDeviceCalendarEvents: StateFlow<Boolean> get() = isShowDeviceCalendarEvents_

private val eventCalendarsIdsToExclude_ = MutableStateFlow(emptyLongSet())
val eventCalendarsIdsToExclude: StateFlow<LongSet> get() = eventCalendarsIdsToExclude_

private val eventCalendarsIdsAsHoliday_ = MutableStateFlow(emptyLongSet())
val eventCalendarsIdsAsHoliday: StateFlow<LongSet> get() = eventCalendarsIdsAsHoliday_

var whatToShowOnWidgets = emptySet<String>()
    private set
var isAstronomicalExtraFeaturesEnabled = false
    private set
var isTalkBackEnabled = false
    private set
var isHighTextContrastEnabled = false
    private set
var shiftWorkTitles = emptyMap<String, String>()
    private set
var shiftWorkStartingJdn: Jdn? = null
    private set
var shiftWorkRecurs = true
    private set
var shiftWorkPeriod = 0
    private set
var isIranHolidaysEnabled = true
    private set
var isAncientIranEnabled = false
    private set
var amString = DEFAULT_AM
    private set
var pmString = DEFAULT_PM
    private set
var spacedAndInDates = " و "
    private set
var spacedOr = " و "
    private set
var spacedColon = ": "
    private set
var spacedComma = "، "
    private set

var nothingScheduledString = ""
    private set
var holidayString = DEFAULT_HOLIDAY
    private set
var numericalDatePreferred = false
    private set
var calendarsTitlesAbbr = emptyList<String>()
    private set

private var secondaryCalendarEnabled = false

// This should be called before any use of Utils on the activity and services
fun initGlobal(context: Context) {
    configureCalendarsAndLoadEvents(context)
}

fun configureCalendarsAndLoadEvents(context: Context) {
    /*debugLog("Utils: configureCalendarsAndLoadEvents is called")
    val preferences = context.preferences

    IslamicDate.islamicOffset = if (preferences.isIslamicOffsetExpired) 0
    else preferences.getString(PREF_ISLAMIC_OFFSET, DEFAULT_ISLAMIC_OFFSET)?.toIntOrNull() ?: 0

    eventsRepository = EventsRepository(preferences, language.value)
    isIranHolidaysEnabled = eventsRepository?.iranHolidays == true
    isAncientIranEnabled = eventsRepository?.iranAncient == true*/
}

fun yearMonthNameOfDate(date: AbstractDate): List<String> {
    return when (date) {
        is PersianDate -> if (date.year > 1303) persianMonths else oldEraPersianMonths
        is CivilDate -> gregorianMonths
        is IslamicDate -> islamicMonths
        else -> monthNameEmptyList
    }
}