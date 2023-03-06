@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.compose.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.outlined.Terrain
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.yyttrium.oriplanner.data.ITaskViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yyttrium.oriplanner.data.Task
import com.yyttrium.oriplanner.ui.compose.Screen


@Composable
fun GoalTask(
    id: Int,
    taskViewModel: ITaskViewModel,
    navController: NavHostController
) {
    val allTasks by taskViewModel.getAll.collectAsState(initial = listOf())
    val filteredTasks = allTasks.filter { task ->
        task.taskGoal == null || task.taskGoal == id
    }

    Column() {
        LazyColumn {
            items(filteredTasks.size) { index ->
                val task = filteredTasks[index]

                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    ListItem(
                        modifier = Modifier.fillMaxWidth(),
                        headlineText = {
                            Text(task.taskName)
                        },
                        trailingContent = {
                            FilledIconToggleButton(
                                checked = task.taskId == id,
                                onCheckedChange = {
                                    taskViewModel.insert(
                                        Task(
                                            taskId = task.taskId,
                                            taskName = task.taskName,
                                            taskDesc = task.taskDesc,
                                            taskDue = task.taskDue,
                                            taskComp = task.taskComp,
                                            taskGoal =
                                            if (task.taskGoal == id)
                                                null
                                            else
                                                id
                                        )
                                    )
                                }
                            ) {
                                Icon(
                                    imageVector =
                                    if (task.taskId == id)
                                        Icons.Outlined.Terrain
                                    else
                                        Icons.Filled.Terrain,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        FilledTonalButton(
            onClick = { navController.navigate(Screen.GoalView.route) },
            modifier = Modifier.padding(32.dp)
        ) {
            Text("Finish")
        }
    }
}