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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme

@Composable
fun OriCard(
    expanded: Boolean,
    overdue: Boolean? = null,
    title: String,
    desc: String?,
    due: String? = null,
    progress: Float? = null,
    onClick: () -> Unit,
    onLongClick: () -> Unit
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
                role = Role.Image,
                onClick = onClick,
                onLongClick = onLongClick
            ),
        colors = CardDefaults.cardColors(
            containerColor =
            if (overdue == true)
                MaterialTheme.colorScheme.error
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
                text = title,
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
                    color = MaterialTheme.colorScheme.tertiary,
                    trackColor = MaterialTheme.colorScheme.onTertiary
                )
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
        }
    }
}

@Preview
@Composable
fun PreviewCardSprintCollapsed() {
    ORIPlannerTheme() {
        OriCard(
            expanded = false,
            overdue = false,
            title = "Sample Sprint",
            desc = null,
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCardSprintExpandedOverdue() {
    ORIPlannerTheme() {
        OriCard(
            expanded = true,
            overdue = true,
            title = "Sample Sprint",
            desc = "sample text\nsample text\nsample text",
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCardTaskCollapsed() {
    ORIPlannerTheme() {
        OriCard(
            expanded = false,
            overdue = false,
            title = "Sample Task",
            desc = null,
            due = "12-25",
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCardTaskExpandedOverdue() {
    ORIPlannerTheme() {
        OriCard(
            expanded = true,
            overdue = true,
            title = "Sample Task",
            desc = "sample text\nsample text\nsample text",
            due = "12-25",
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCardGoalCollapsed() {
    ORIPlannerTheme() {
        OriCard(
            expanded = false,
            title = "Sample Goal",
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
    ORIPlannerTheme() {
        OriCard(
            expanded = true,
            title = "Sample Goal",
            desc = "sample text\nsample text\nsample text",
            progress = 0.65f,
            onClick = {},
            onLongClick = {}
        )
    }
}