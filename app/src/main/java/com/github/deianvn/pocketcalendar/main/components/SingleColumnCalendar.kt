package com.github.deianvn.pocketcalendar.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.deianvn.pocketcalendar.main.state.Now
import java.time.Year

@Composable
fun SingleColumnCalendar(
    now: Now,
    shownYear: Year
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MonthHeader(isCompact = false)
        LazyColumn(
            modifier = Modifier.weight(1F)
        ) {
            items(12) {
                MonthBody(
                    shownYear.atMonth(it + 1),
                    isCurrentMonth = now.year == shownYear && it == now.month.monthValue - 1,
                    currentDay = if (now.year == shownYear && it == now.month.monthValue - 1) now.day.dayOfMonth else null,
                    isCompact = false
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
