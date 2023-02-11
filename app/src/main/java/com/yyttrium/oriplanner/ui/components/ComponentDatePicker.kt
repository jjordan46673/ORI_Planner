package com.yyttrium.oriplanner.ui.components

import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.yyttrium.oriplanner.R
import java.time.LocalDate

@Composable
fun DatePicker(
    onDateSelected: (String) -> Unit,
    selectedDate: String
) {
    Dialog(
        onDismissRequest = { onDateSelected(selectedDate) },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.large
                )
        ) {
            Column(
                modifier = Modifier
                    .defaultMinSize(minHeight = 72.dp)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.large.copy(
                            bottomStart = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp)
                        )
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "SELECT DATE",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.size(24.dp))

                Text(
                    text = formatDate(selectedDate),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.size(16.dp))
            }

            CustomCalendarView(
                onDateSelected = onDateSelected,
                selectedDate = selectedDate
            )

            Spacer(modifier = Modifier.size(8.dp))

        }
    }
}

@Composable
fun CustomCalendarView(
    onDateSelected: (String) -> Unit,
    selectedDate: String
) {
    AndroidView(
        factory = { CalendarView(ContextThemeWrapper(it, R.style.Theme_ORIPlanner_Dialog)) },
        modifier = Modifier.wrapContentWidth(),
        update = { views ->
            views.date = (LocalDate.parse(selectedDate).toEpochDay() + 1) * 86400000

            views.setOnDateChangeListener { calendarView, year, month, day ->
                onDateSelected(
                    LocalDate
                        .now()
                        .withMonth(month + 1)
                        .withYear(year)
                        .withDayOfMonth(day)
                        .toString()
                )
            }
        }
    )
}

fun formatDate(date: String): String {
    var output = ""

    when (date.substring(5, 7)) {
        "01" -> output = "Jan "
        "02" -> output = "Feb "
        "03" -> output = "Mar "
        "04" -> output = "Apr "
        "05" -> output = "May "
        "06" -> output = "Jun "
        "07" -> output = "Jul "
        "08" -> output = "Aug "
        "09" -> output = "Sep "
        "10" -> output = "Oct "
        "11" -> output = "Nov "
        "12" -> output = "Dec "
    }

    output += date.substring(8)
    output += ", "
    output += date.substring(0,4)
    return output
}

