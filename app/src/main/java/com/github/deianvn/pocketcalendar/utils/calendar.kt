package com.github.deianvn.pocketcalendar.utils

import java.time.DayOfWeek

fun DayOfWeek.adjustedDayOfWeekValue(firstDayOfWeek: DayOfWeek): Int {
    val firstDayValue = firstDayOfWeek.value
    val dayValue = value
    val diff = dayValue - firstDayValue

    return if (diff >= 0) {
        diff + 1
    } else {
        8 + diff
    }
}
