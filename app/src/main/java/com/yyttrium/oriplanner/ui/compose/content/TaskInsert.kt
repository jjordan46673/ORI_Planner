package com.yyttrium.oriplanner.ui.compose.content

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.data.*
import com.yyttrium.oriplanner.ui.compose.Screen
import com.yyttrium.oriplanner.ui.compose.content.components.OriDateDialog
import com.yyttrium.oriplanner.ui.compose.content.components.OriDateField
import com.yyttrium.oriplanner.ui.compose.content.components.OriInsertFields
import com.yyttrium.oriplanner.ui.compose.content.components.OriInsertNav
import java.time.LocalDate

@Composable
fun TaskInsert(
    id: Int,
    taskViewModel: ITaskViewModel,
    navController: NavController
) {
    var showDateDialog by remember { mutableStateOf(false) }

    taskViewModel.id = id

    val task: Task =
        if (id != 0)
            taskViewModel.getTask.collectAsState(
                initial = Task(taskName = "", taskDue = "")
            ).value
        else
            Task(taskName = "", taskDue = "")

    var taskName by remember { mutableStateOf("") }
    var taskDesc by remember { mutableStateOf("") }
    var taskDue by remember { mutableStateOf("") }

    val taskId: Int = task.taskId
    taskName = task.taskName
    taskDesc = task.taskDesc ?: ""
    taskDue =
        if (task.taskDue == "") LocalDate.now().toString()
        else task.taskDue
    val taskComp: Boolean = task.taskComp
    val taskGoal: Int? = task.taskGoal

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OriInsertFields(
                id = taskId,
                type = "Task",
                body = stringResource(R.string.body_task),
                hint = stringResource(R.string.hint_task),
                name = taskName,
                desc = taskDesc,
                onNameChange = { taskName = it },
                onDescChange = { taskDesc = it }
            )

            OriDateField(
                dueDate = taskDue,
                onShowDateDialog = { showDateDialog = true }
            )

            Spacer(modifier = Modifier.weight(1f))

            OriInsertNav(
                onSave = {
                    taskViewModel.insert(
                        Task(
                            taskId = taskId,
                            taskName = taskName,
                            taskDesc = if (taskDesc == "") null else taskDesc,
                            taskDue = taskDue,
                            taskComp = taskComp,
                            taskGoal = taskGoal
                        )
                    )
                    navController.navigate(Screen.TaskView.route)
                },
                onExit = { navController.navigate(Screen.TaskView.route) }
            )
        }

        if (showDateDialog) {
            OriDateDialog(
                onDateSelected = {
                    taskDue = it
                    showDateDialog = false
                },
                selectedDate = taskDue
            )
        }
    }
}