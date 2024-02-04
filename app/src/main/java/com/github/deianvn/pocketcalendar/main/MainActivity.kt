package com.github.deianvn.pocketcalendar.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.github.deianvn.pocketcalendar.main.components.FullSizeCalendar
import com.github.deianvn.pocketcalendar.main.components.SingleColumnCalendar
import com.github.deianvn.pocketcalendar.main.components.Toolbar
import com.github.deianvn.pocketcalendar.main.state.CalendarState
import com.github.deianvn.pocketcalendar.main.state.Now
import java.time.Year


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            MaterialTheme {

                var calendarState by rememberSaveable {
                    mutableStateOf(CalendarState())
                }

                var isFullCalendarView by rememberSaveable {
                    mutableStateOf(false)
                }

                Scaffold(
                    topBar = {
                        Toolbar(
                            shownYear = calendarState.shownYear, isFullCalendarView = isFullCalendarView
                        ) {
                            isFullCalendarView = it
                        }
                    }
                ) { innerPaddings ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPaddings)
                    ) {
                        CalendarPager(
                            calendarState.now, calendarState.shownYear, isFullCalendarView,
                            onShownYearChange = {
                                calendarState = calendarState.copy(shownYear = it)
                            },
                            onMonthClicked = {
//                                isFullCalendarView = false
                            }
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun CalendarPager(
        now: Now,
        shownYear: Year,
        isFullCalendarView: Boolean,
        onShownYearChange: (Year) -> Unit,
        onMonthClicked: (month: Int) -> Unit
    ) {

        val firstYear = 1900
        val lastYear = 2100
        val totalYears = lastYear - firstYear

        val pagerState = rememberPagerState(
            pageCount = { totalYears },
            initialPage = shownYear.value - firstYear
        )

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                onShownYearChange(
                    Year.of(page + firstYear)
                )
            }
        }

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { page ->
            if (isFullCalendarView) {
                FullSizeCalendar(
                    now = now,
                    shownYear = Year.of(page + firstYear),
                    onMonthClicked = onMonthClicked
                )
            } else {
                SingleColumnCalendar(
                    now,
                    Year.of(page + firstYear)
                )
            }
        }
    }

}
