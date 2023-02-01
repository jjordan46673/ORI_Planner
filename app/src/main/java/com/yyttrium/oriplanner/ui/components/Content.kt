@file:OptIn(ExperimentalFoundationApi::class)

package com.yyttrium.oriplanner.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yyttrium.oriplanner.data.*

@Composable
fun SprintView(
    sprintViewModel: ISprintViewModel
) {
    val allSprints by sprintViewModel.getAll.collectAsState(initial = listOf())

    LazyColumn {
        items(allSprints.size) { index ->
            var expanded by remember { mutableStateOf(false) }
            val sprint = allSprints[index]

            Row(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .combinedClickable(
                            onClick = { expanded = !expanded },
                            // TODO edit dialog
                            onLongClick = {}
                        )
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioLowBouncy,
                                stiffness = Spring.StiffnessMediumLow
                            )
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {

                        Text(
                            text = sprint.sprintName,
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.titleLarge.copy()
                        )

                        if (expanded) {
                            if ((sprint.sprintDesc != null) && (sprint.sprintDesc != "")) {
                                // Sprint Description
                                Surface(
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    shape = MaterialTheme.shapes.medium
                                ) {
                                    Text(
                                        text = sprint.sprintDesc,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                        }

                    }
                }

                Checkbox(
                    checked = sprint.sprintComp,
                    onCheckedChange = {
                        sprintViewModel.update(
                            Sprint(
                                sprintId = sprint.sprintId,
                                sprintName = sprint.sprintName,
                                sprintDesc = sprint.sprintDesc,
                                sprintDue = sprint.sprintDue,
                                sprintComp = !sprint.sprintComp
                            )
                        )
                    },
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

            }
        }
    }
}

@Composable
fun TaskView(
    taskViewModel: ITaskViewModel
) {
    val allTasks by taskViewModel.getAll.collectAsState(initial = listOf())

    LazyColumn {
        items(allTasks.size) { index ->
            var expanded by remember { mutableStateOf(false) }
            val task = allTasks[index]

            Row(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .combinedClickable(
                            onClick = { expanded = !expanded },
                            onLongClick = {}
                        )
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioLowBouncy,
                                stiffness = Spring.StiffnessMediumLow
                            )
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {

                        Text(
                            text = task.taskName,
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.titleLarge.copy()
                        )

                        Text(
                            text = task.taskDue,
                            style = MaterialTheme.typography.titleSmall
                        )

                        if (expanded) {
                            if ((task.taskDesc != null) && (task.taskDesc != "")) {
                                // Sprint Description
                                Surface(
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    shape = MaterialTheme.shapes.medium
                                ) {
                                    Text(
                                        text = task.taskDesc,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                        }

                    }
                }

                Checkbox(
                    checked = task.taskComp,
                    onCheckedChange = {
                        taskViewModel.update(
                            Task(
                                taskId = task.taskId,
                                taskName = task.taskName,
                                taskDesc = task.taskDesc,
                                taskDue = task.taskDue,
                                taskComp = !task.taskComp
                            )
                        )
                    },
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

            }
        }
    }
}

@Composable
fun GoalView(
    goalViewModel: IGoalViewModel
) {
    Text(
        text = "sample_text, goal view"
    )
}