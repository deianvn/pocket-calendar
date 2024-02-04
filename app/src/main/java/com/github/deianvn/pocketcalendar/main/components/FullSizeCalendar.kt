package com.github.deianvn.pocketcalendar.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.deianvn.pocketcalendar.main.state.Now
import java.time.Year

@Composable
fun FullSizeCalendar(
    now: Now,
    shownYear: Year,
    onMonthClicked: (month: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 3.dp)
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MonthsRow(
                modifier = Modifier.weight(1f),
                start = 1, now = now,
                shownYear = shownYear,
                onMonthClicked = onMonthClicked
            )
            MonthsRow(
                modifier = Modifier.weight(1f),
                start = 4,
                now = now,
                shownYear = shownYear,
                onMonthClicked = onMonthClicked
            )
            MonthsRow(
                modifier = Modifier.weight(1f),
                start = 7,
                now = now,
                shownYear = shownYear,
                onMonthClicked = onMonthClicked
            )
            MonthsRow(
                modifier = Modifier.weight(1f),
                start = 10,
                now = now,
                shownYear = shownYear,
                onMonthClicked = onMonthClicked
            )
        }
    }
}

@Composable
private fun MonthsRow(
    modifier: Modifier = Modifier,
    start: Int,
    now: Now,
    shownYear: Year,
    onMonthClicked: (month: Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
//                .clickable { onMonthClicked(start) }
        ) {
            MonthBody(
                shownYear.atMonth(start),
                isCurrentMonth = now.year == shownYear && start == now.month.monthValue,
                currentDay = if (now.year == shownYear && start == now.month.monthValue) now.day.dayOfMonth else null,
                isCompact = true
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
//                .clickable { onMonthClicked(start + 1) }
        ) {
            MonthBody(
                shownYear.atMonth(start + 1),
                isCurrentMonth = now.year == shownYear && start + 1 == now.month.monthValue,
                currentDay = if (now.year == shownYear && start + 1 == now.month.monthValue) now.day.dayOfMonth else null,
                isCompact = true
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
//                .clickable { onMonthClicked(start + 2) }
        ) {
            MonthBody(
                shownYear.atMonth(start + 2),
                isCurrentMonth = now.year == shownYear && start + 2 == now.month.monthValue,
                currentDay = if (now.year == shownYear && start + 2 == now.month.monthValue) now.day.dayOfMonth else null,
                isCompact = true
            )
        }
    }
}