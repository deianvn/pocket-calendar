package com.github.deianvn.pocketcalendar.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.deianvn.pocketcalendar.R
import java.time.Year

@Composable
fun Toolbar(
    shownYear: Year,
    isFullCalendarView: Boolean,
    onLayoutChange: (isFullCalendarView: Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.primaryVariant))
            .padding(start = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = shownYear.toString(),
            fontSize = 19.sp,
            color = colorResource(id = R.color.colorOnPrimaryVariant)
        )
        IconButton(onClick = { onLayoutChange(!isFullCalendarView) }) {
            Icon(
                painter = painterResource(
                    id = if (isFullCalendarView) R.drawable.ic_zoom_in_light_24 else R.drawable.ic_zoom_out_light_24
                ),
                tint = colorResource(id = R.color.colorOnPrimaryVariant),
                contentDescription = null
            )
        }
    }
}
