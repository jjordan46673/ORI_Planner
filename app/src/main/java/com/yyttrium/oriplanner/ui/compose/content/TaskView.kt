package com.yyttrium.oriplanner.ui.compose.content

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yyttrium.oriplanner.data.*
import com.yyttrium.oriplanner.ui.compose.Screen
import com.yyttrium.oriplanner.ui.compose.content.components.OriCard
import com.yyttrium.oriplanner.ui.compose.content.components.OriCheckbox
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
                Box(modifier = Modifier.weight(1f)) {
                    OriCard(
                        expanded = expanded,
                        overdue = ((task.taskDue.compareTo(LocalDate.now().toString())) >= 0),
                        title = task.taskName,
                        desc = task.taskDesc,
                        due = task.taskDue.drop(5),
                        onClick = { expanded = !expanded },
                        onLongClick = {
                            navController.navigate(
                                Screen.TaskInsert.route + task.taskId.toString()
                            )
                        }
                    )
                }

                OriCheckbox(
                    checked = task.taskComp,
                    expanded = expanded,
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
                    },
                    onDelete = {
                        taskViewModel.delete((task))
                    }
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(64.dp))
}