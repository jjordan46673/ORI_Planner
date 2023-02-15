@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.compose.content.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme

@Composable
fun OriDateField(
    dueDate: String,
    onShowDateDialog: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = formatDate(dueDate),
            onValueChange = {},
            modifier = Modifier
                .clickable(
                    onClick = onShowDateDialog
                )
                .padding(8.dp)
                .weight(5f),
            readOnly = true,
            label = { Text("Due By") },
            singleLine = true
        )
        FilledTonalButton(
            onClick = onShowDateDialog,
            modifier = Modifier
                .padding(8.dp)
                .weight(4f)
        ) {
            Icon(
                imageVector = Icons.Rounded.CalendarToday,
                contentDescription = null
            )
        }
    }
}

fun formatDate(date: String): String {
    val month: String? = when (date.substring(5, 7)) {
        "01" -> "Jan"
        "02" -> "Feb"
        "03" -> "Mar"
        "04" -> "Apr"
        "05" -> "May"
        "06" -> "Jun"
        "07" -> "Jul"
        "08" -> "Aug"
        "09" -> "Sep"
        "10" -> "Oct"
        "11" -> "Nov"
        "12" -> "Dec"
        else -> null
    }

    return "$month ${date.substring(8)}, ${date.substring(0, 4)}"
}

@Preview
@Composable
fun PreviewDateField() {
    ORIPlannerTheme {
        Surface {
            OriDateField(
                dueDate = "1999-12-25",
                onShowDateDialog = {}
            )
        }
    }
}