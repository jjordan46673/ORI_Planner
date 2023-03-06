@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.compose.content.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yyttrium.oriplanner.data.ITaskViewModel
import com.yyttrium.oriplanner.data.Task
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme

@Composable
fun OriList(
    tasks: List<Task>,
    taskViewModel: ITaskViewModel? = null
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        LazyColumn {
            items(tasks.size) { index ->
                val task = tasks[index]
                val due = task.taskDue.drop(5)

                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineText = {
                        Text(task.taskName)
                    },
                    supportingText = {
                        Text(
                            "Due · $due"
                        )
                    },
                    trailingContent = {
                        Checkbox(
                            checked = task.taskComp,
                            onCheckedChange = {
                                taskViewModel?.insert(
                                    Task(
                                        taskId = task.taskId,
                                        taskName = task.taskName,
                                        taskDesc = task.taskDesc,
                                        taskDue = task.taskDue,
                                        taskComp = !task.taskComp,
                                        taskGoal = task.taskGoal
                                    )
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OriListTasks() {
    ORIPlannerTheme {
        OriList(
            tasks = listOf(
                Task(taskId = 1, taskName = "Sample One", taskDue = "2023-12-23", taskComp = true),
                Task(taskId = 2, taskName = "Sample Two", taskDue = "2023-12-24", taskComp = false),
                Task(
                    taskId = 3,
                    taskName = "Sample Three",
                    taskDue = "2023-12-21",
                    taskComp = false
                ),
            )
        )
    }
}