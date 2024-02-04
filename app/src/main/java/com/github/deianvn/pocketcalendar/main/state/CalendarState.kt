package com.github.deianvn.pocketcalendar.main.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.MonthDay
import java.time.Year
import java.time.YearMonth

@Parcelize
data class Now(
    val year: Year = Year.now(),
    val month: YearMonth = YearMonth.now(),
    val day: MonthDay = MonthDay.now()
): Parcelable

@Parcelize
data class CalendarState(
    val now: Now = Now(),
    var shownYear: Year = now.year
): Parcelable
