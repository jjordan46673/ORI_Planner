@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.compose.content.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme

@Composable
fun OriInsertFields(
    id: Int,
    type: String,
    body: String,
    hint: String,
    name: String,
    desc: String,
    onNameChange: (String) -> Unit,
    onDescChange: (String) -> Unit
) {
    val new: Boolean = (id == 0)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text =
            if (new)
                "Add $type"
            else
                "Edit $type",
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = body,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Text(
            text = hint,
            modifier = Modifier.padding(4.dp),
            fontStyle = FontStyle.Italic
        )
        TextField(
            value = name,
            onValueChange = onNameChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            label = { Text("My Objective") },
            singleLine = true
        )
        TextField(
            value = desc,
            onValueChange = onDescChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            label = { Text("Description") },
            singleLine = false
        )
    }
}

@Preview
@Composable
fun PreviewGreetingAddSprint() {
    ORIPlannerTheme {
        Surface() {
            OriInsertFields(
                id = 0,
                type = "Sprint",
                body = stringResource(R.string.body_sprint),
                hint = stringResource(R.string.hint_sprint),
                name = "",
                desc = "",
                onNameChange = {},
                onDescChange = {}
            )
        }
    }
}

@Preview
@Composable
fun PreviewGreetingAddTask() {
    ORIPlannerTheme {
        Surface() {
            OriInsertFields(
                id = 0,
                type = "Task",
                body = stringResource(R.string.body_task),
                hint = stringResource(R.string.hint_task),
                name = "",
                desc = "",
                onNameChange = {},
                onDescChange = {}
            )
        }
    }
}

@Preview
@Composable
fun PreviewGreetingAddGoal() {
    ORIPlannerTheme {
        Surface() {
            OriInsertFields(
                id = 0,
                type = "Goal",
                body = stringResource(R.string.body_goal),
                hint = stringResource(R.string.hint_goal),
                name = "",
                desc = "",
                onNameChange = {},
                onDescChange = {}
            )
        }

    }
}

@Preview
@Composable
fun PreviewGreetingEditSprint() {
    ORIPlannerTheme {
        Surface() {
            OriInsertFields(
                id = 1,
                type = "Sprint",
                body = stringResource(R.string.body_sprint),
                hint = stringResource(R.string.hint_sprint),
                name = "Sample Sprint",
                desc = "sample text\nsample text\nsample text",
                onNameChange = {},
                onDescChange = {}
            )
        }
    }
}

@Preview
@Composable
fun PreviewGreetingEditTask() {
    ORIPlannerTheme {
        Surface() {
            OriInsertFields(
                id = 1,
                type = "Task",
                body = stringResource(R.string.body_task),
                hint = stringResource(R.string.hint_task),
                name = "Sample Task",
                desc = "sample text\nsample text\nsample text",
                onNameChange = {},
                onDescChange = {}
            )
        }
    }
}

@Preview
@Composable
fun PreviewGreetingEditGoal() {
    ORIPlannerTheme {
        Surface() {
            OriInsertFields(
                id = 1,
                type = "Goal",
                body = stringResource(R.string.body_goal),
                hint = stringResource(R.string.hint_goal),
                name = "Sample Goal",
                desc = "sample text\nsample text\nsample text",
                onNameChange = {},
                onDescChange = {}
            )
        }
    }
}