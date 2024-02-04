package com.github.deianvn.pocketcalendar.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.deianvn.pocketcalendar.R
import com.github.deianvn.pocketcalendar.styles.MONTH_DAY_FONT_SIZE
import com.github.deianvn.pocketcalendar.styles.MONTH_DAY_FONT_SIZE_SMALL
import com.github.deianvn.pocketcalendar.styles.MONTH_HEADER_PADDING_BOTTOM
import com.github.deianvn.pocketcalendar.styles.MONTH_HEADER_PADDING_BOTTOM_SMALL
import com.github.deianvn.pocketcalendar.styles.MONTH_WEEK_PADDING_H
import com.github.deianvn.pocketcalendar.styles.MONTH_WEEK_PADDING_H_SMALL

@Composable
fun MonthHeader(isCompact: Boolean) {

    val daysAbbr = listOf("M", "T", "W", "T", "F", "S", "S")

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.primaryVariant))
                .padding(
                    start = if (isCompact) MONTH_WEEK_PADDING_H_SMALL else MONTH_WEEK_PADDING_H,
                    end = if (isCompact) MONTH_WEEK_PADDING_H_SMALL else MONTH_WEEK_PADDING_H,
                    bottom = if (isCompact) MONTH_HEADER_PADDING_BOTTOM_SMALL else MONTH_HEADER_PADDING_BOTTOM
                )
        ) {
            daysAbbr.forEach {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it,
                        fontSize = if (isCompact) MONTH_DAY_FONT_SIZE_SMALL else MONTH_DAY_FONT_SIZE,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
