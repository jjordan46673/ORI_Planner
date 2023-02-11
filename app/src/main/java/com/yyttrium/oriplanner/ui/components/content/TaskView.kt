@file:OptIn(ExperimentalFoundationApi::class)

package com.yyttrium.oriplanner.ui.components.content

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yyttrium.oriplanner.data.*
import com.yyttrium.oriplanner.ui.components.Screen
import java.time.LocalDate

@Composable
fun TaskView(
    taskViewModel: ITaskViewModel,
    navController: NavHostController
) {
    val allTasks by taskViewModel.getAll.collectAsState(initial = listOf())

    LazyColumn {
        items(allTasks.size) { index ->
            var expanded by remember { mutableStateOf(false) }
            val task = allTasks[index]

            Row(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .combinedClickable(
                            onClick = { expanded = !expanded },
                            onLongClick = {
                                navController.navigate(
                                    Screen.TaskInsert.route +
                                            task.taskId.toString()
                                )
                            }
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor =
                        if ((task.taskDue.compareTo(LocalDate.now().toString())) >= 0)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.error
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = task.taskName,
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = task.taskDue.drop(5),
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.labelLarge
                        )
                        if (expanded) {
                            if ((task.taskDesc != null) && (task.taskDesc != "")) {
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

                Column(
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Checkbox(
                        checked = task.taskComp,
                        onCheckedChange = {
                            taskViewModel.insert(
                                Task(
                                    taskId = task.taskId,
                                    taskName = task.taskName,
                                    taskDesc = task.taskDesc,
                                    taskDue = task.taskDue,
                                    taskComp = !task.taskComp
                                )
                            )
                        }
                    )
                    if (expanded) {
                        IconButton(
                            onClick = { taskViewModel.delete(task) },
                            modifier = Modifier,
                            enabled = task.taskComp,
                            content = { Icon(Icons.Rounded.Delete, null) }
                        )
                    }
                }
            }
        }
    }
}