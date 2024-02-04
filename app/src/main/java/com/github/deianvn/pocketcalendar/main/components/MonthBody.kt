package com.github.deianvn.pocketcalendar.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.deianvn.pocketcalendar.R
import com.github.deianvn.pocketcalendar.styles.MONTH_DAY_FONT_SIZE
import com.github.deianvn.pocketcalendar.styles.MONTH_DAY_FONT_SIZE_SMALL
import com.github.deianvn.pocketcalendar.styles.MONTH_DAY_PADDING_BOTTOM
import com.github.deianvn.pocketcalendar.styles.MONTH_DAY_PADDING_BOTTOM_SMALL
import com.github.deianvn.pocketcalendar.styles.MONTH_DAY_PADDING_TOP
import com.github.deianvn.pocketcalendar.styles.MONTH_DAY_PADDING_TOP_SMALL
import com.github.deianvn.pocketcalendar.styles.MONTH_DAY_SELECTED_OVAL_SIZE
import com.github.deianvn.pocketcalendar.styles.MONTH_DAY_SELECTED_OVAL_SIZE_SMALL
import com.github.deianvn.pocketcalendar.styles.MONTH_HEADER_FONT_SIZE
import com.github.deianvn.pocketcalendar.styles.MONTH_HEADER_FONT_SIZE_COMPACT
import com.github.deianvn.pocketcalendar.styles.MONTH_TITLE_PADDING_BOTTOM
import com.github.deianvn.pocketcalendar.styles.MONTH_TITLE_PADDING_TOP
import com.github.deianvn.pocketcalendar.styles.MONTH_WEEK_PADDING_H
import com.github.deianvn.pocketcalendar.styles.MONTH_WEEK_PADDING_H_SMALL
import com.github.deianvn.pocketcalendar.utils.adjustedDayOfWeekValue
import java.time.DayOfWeek
import java.time.YearMonth

@Composable
fun MonthBody(
    month: YearMonth,
    isCurrentMonth: Boolean = false,
    currentDay: Int? = null,
    isCompact: Boolean
) {
    val totalDays = month.lengthOfMonth()
    val firstDay = month.atDay(1).dayOfWeek
    val lastDay = month.atDay(totalDays).dayOfWeek
    val offsetStart = firstDay.adjustedDayOfWeekValue(DayOfWeek.MONDAY) - 1
    val offsetEnd = 7 - lastDay.adjustedDayOfWeekValue(DayOfWeek.MONDAY)
    val cellsCount = offsetStart + totalDays + offsetEnd

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MonthTitle(month.monthValue, offsetStart, isCurrentMonth, isCompact)
        Week(1, offsetStart, 0, currentDay, isCompact)
        var day = 8 - offsetStart
        repeat(cellsCount / 7 - 2) {
            Week(day, 0, 0, currentDay, isCompact)
            day += 7
        }
        if (offsetEnd < 7) {
            Week(totalDays - 6 + offsetEnd, 0, offsetEnd, currentDay, isCompact)
        }
    }
}

@Composable
private fun MonthTitle(
    month: Int, offset: Int, isCurrentMonth: Boolean, isCompact: Boolean
) {

    val months =
        listOf("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC")

    if (isCompact) {
        Text(
            modifier = Modifier.padding(start = MONTH_WEEK_PADDING_H_SMALL),
            text = months[month - 1],
            fontSize = MONTH_HEADER_FONT_SIZE_COMPACT,
            fontWeight = FontWeight.Bold,
            color = if (isCurrentMonth) {
                colorResource(id = R.color.colorOnPrimaryVariant)
            } else {
                colorResource(id = R.color.colorOnPrimary)
            }
        )
    } else {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .width(
                        MONTH_WEEK_PADDING_H
                    )
            )
            for (emptyCell in 0 until offset) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(
                        top = MONTH_TITLE_PADDING_TOP,
                        bottom = MONTH_TITLE_PADDING_BOTTOM
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = months[month - 1],
                    fontSize = MONTH_HEADER_FONT_SIZE,
                    fontWeight = FontWeight.Bold,
                    color = if (isCurrentMonth) {
                        colorResource(id = R.color.colorOnPrimaryVariant)
                    } else {
                        colorResource(id = R.color.colorOnPrimary)
                    }
                )
            }

            for (emptyCell in offset until 6) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {

                }
            }

            Spacer(
                modifier = Modifier
                    .width(
                        MONTH_WEEK_PADDING_H
                    )
            )
        }
    }
}

@Composable
private fun Week(startDay: Int, offsetStart: Int, offsetEnd: Int, currentDay: Int?, isCompact: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .width(
                    if (isCompact) MONTH_WEEK_PADDING_H_SMALL else MONTH_WEEK_PADDING_H
                )
                .align(Alignment.Top)
        ) {
            if (!isCompact && offsetStart == 0) {
                CellBorder()
            }
        }
        for (emptyDay in 0 until offsetStart) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        for (day in startDay..startDay + 6 - offsetStart - offsetEnd) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Day(day = day, isCurrentDay = day == currentDay, isCompact)
            }
        }
        for (emptyDay in 0 until offsetEnd) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        Box(
            modifier = Modifier
                .width(
                    if (isCompact) MONTH_WEEK_PADDING_H_SMALL else MONTH_WEEK_PADDING_H
                )
                .align(Alignment.Top)
        ) {
            if (!isCompact && offsetEnd == 0) {
                CellBorder()
            }
        }
    }
}

@Composable
private fun Day(day: Int, isCurrentDay: Boolean, isCompact: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isCompact) {
            CellBorder()
        }
        Spacer(
            modifier = Modifier.height(
                if (isCompact) MONTH_DAY_PADDING_TOP_SMALL else MONTH_DAY_PADDING_TOP
            )
        )
        Box(
            modifier = if (isCurrentDay) {
                Modifier
                    .size(
                        if (isCompact) MONTH_DAY_SELECTED_OVAL_SIZE_SMALL else MONTH_DAY_SELECTED_OVAL_SIZE
                    )
                    .clip(shape = CircleShape)
                    .background(colorResource(id = R.color.colorOnPrimaryVariant))
            } else {
                Modifier.size(
                    if (isCompact) MONTH_DAY_SELECTED_OVAL_SIZE_SMALL else MONTH_DAY_SELECTED_OVAL_SIZE
                )
            },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = day.toString(),
                fontSize = if (isCompact) MONTH_DAY_FONT_SIZE_SMALL else MONTH_DAY_FONT_SIZE,
                color = if (isCurrentDay) {
                    colorResource(id = R.color.colorOnSecondary)
                } else {
                    colorResource(id = R.color.colorOnPrimary)
                }
            )
        }
        Spacer(
            modifier = Modifier.height(
                if (isCompact) MONTH_DAY_PADDING_BOTTOM_SMALL else MONTH_DAY_PADDING_BOTTOM
            )
        )
    }
}

@Composable
private fun CellBorder() {
    Divider(
        modifier = Modifier.fillMaxWidth(),
        color = colorResource(id = R.color.primaryVariant),
        thickness = 1.dp
    )
}
