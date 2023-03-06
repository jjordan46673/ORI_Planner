@file:OptIn(ExperimentalFoundationApi::class)

package com.yyttrium.oriplanner.ui.compose.content.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyttrium.oriplanner.data.Task
import com.yyttrium.oriplanner.data.TaskViewModel
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme

// TODO finish check tasks in goal
@Composable
fun OriCard(
    expanded: Boolean,
    overdue: Boolean? = null,
    name: String,
    desc: String?,
    due: String? = null,
    comp: Boolean? = null,
    progress: Float? = null,
    tasks: List<Task>? = null,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    taskViewModel: TaskViewModel? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        colors = CardDefaults.cardColors(
            containerColor =
            if (overdue == true && comp == false)
                MaterialTheme.colorScheme.error
            else if (comp == true)
                MaterialTheme.colorScheme.tertiary
            else
                MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = name,
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 0.dp),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge
            )
            if (due != null) {
                Text(
                    text = "Due · $due",
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 0.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (progress != null) {
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .padding(horizontal = 8.dp),
                    color = MaterialTheme.colorScheme.inversePrimary,
                    trackColor = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (expanded) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            if (expanded && (desc != null)) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Text(
                        text = desc,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            if (expanded && (tasks != null)) {
                Spacer(modifier = Modifier.height(8.dp))
                OriList(
                    tasks = tasks,
                    taskViewModel = taskViewModel!!
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCardSprintCollapsed() {
    ORIPlannerTheme {
        OriCard(
            expanded = false,
            overdue = false,
            name = "Sample Sprint",
            desc = null,
            comp = false,
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCardSprintExpandedOverdue() {
    ORIPlannerTheme {
        OriCard(
            expanded = true,
            overdue = true,
            name = "Sample Sprint",
            desc = "sample text\nsample text\nsample text",
            comp = false,
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCardTaskCollapsed() {
    ORIPlannerTheme {
        OriCard(
            expanded = false,
            overdue = false,
            name = "Sample Task",
            desc = null,
            due = "12-25",
            comp = false,
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCardTaskExpandedOverdue() {
    ORIPlannerTheme {
        OriCard(
            expanded = true,
            overdue = true,
            name = "Sample Task",
            desc = "sample text\nsample text\nsample text",
            due = "12-25",
            comp = false,
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCardGoalCollapsed() {
    ORIPlannerTheme {
        OriCard(
            expanded = false,
            name = "Sample Goal",
            desc = null,
            progress = 0.4f,
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCardGoalExpanded() {
    ORIPlannerTheme {
        OriCard(
            expanded = true,
            name = "Sample Goal",
            desc = "sample text\nsample text\nsample text",
            progress = 0.65f,
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCardSprintOverdue() {
    ORIPlannerTheme {
        OriCard(
            expanded = false,
            overdue = true,
            name = "Sample Sprint",
            desc = null,
            comp = false,
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCardSprintComplete() {
    ORIPlannerTheme {
        OriCard(
            expanded = false,
            overdue = true,
            name = "Sample Sprint",
            desc = null,
            comp = true,
            onClick = {},
            onLongClick = {}
        )
    }
}