package com.yyttrium.oriplanner.ui.compose.content.components

import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme
import java.time.LocalDate

@Composable
fun OriDateDialog(
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
        Surface(
            modifier = Modifier.wrapContentSize(),
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Column {
                        Text(
                            text = "Select Date",
                            modifier = Modifier
                                .padding(
                                    top = 8.dp, start = 8.dp, end = 8.dp, bottom = 0.dp
                                ),
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = formatDate(selectedDate),
                            modifier = Modifier
                                .padding(
                                    top = 0.dp, start = 8.dp, end = 8.dp, bottom = 16.dp
                                ),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                CalendarView(
                    onDateSelected = onDateSelected,
                    selectedDate = selectedDate
                )
            }
        }
    }
}

@Composable
fun CalendarView(
    onDateSelected: (String) -> Unit,
    selectedDate: String
) {
    AndroidView(
        factory = { CalendarView(ContextThemeWrapper(it, R.style.Theme_ORIPlanner_Dialog)) },
        modifier = Modifier.wrapContentWidth(),
        update = { views ->
            views.date = (LocalDate.parse(selectedDate).toEpochDay() + 1) * 86400000

            views.setOnDateChangeListener { _, year, month, day ->
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

@Preview
@Composable
fun PreviewDateDialog() {
    ORIPlannerTheme {
        OriDateDialog(onDateSelected = {}, selectedDate = "1999-12-25")
    }
}