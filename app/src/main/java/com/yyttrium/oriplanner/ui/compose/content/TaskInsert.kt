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
    taskViewModel: ITaskViewModel,
    id: Int,
    navController: NavController
) {
    var showDateDialog by remember { mutableStateOf(false) }
    val allTasks by taskViewModel.getAll.collectAsState(initial = listOf())

    fun findTask(id: Int): Task {
        var output = Task(taskName = "", taskDue = "")
        for (task in allTasks) {
            if (task.taskId == id) {
                output = task
                break
            }
        }
        return output
    }

    val editTask: Task =
        if (id != 0) findTask(id)
        else Task(taskName = "", taskDue = "")

    val TaskId: Int = editTask.taskId
    val TaskComp: Boolean = editTask.taskComp

    var TaskName by remember { mutableStateOf("") }
    TaskName = editTask.taskName
    var TaskDesc by remember { mutableStateOf("") }
    TaskDesc = editTask.taskDesc ?: ""
    var TaskDue by remember { mutableStateOf("") }
    TaskDue =
        if (editTask.taskDue == "") LocalDate.now().toString()
        else editTask.taskDue

    fun returnToView() {
        navController.navigate(Screen.TaskView.route)
    }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OriInsertFields(
                id = TaskId,
                type = "Task",
                body = stringResource(R.string.body_task),
                hint = stringResource(R.string.hint_task),
                name = TaskName,
                desc = TaskDesc,
                onNameChange = { TaskName = it },
                onDescChange = { TaskDesc = it }
            )

            OriDateField(
                dueDate = TaskDue,
                onShowDateDialog = { showDateDialog = true }
            )

            Spacer(modifier = Modifier.weight(1f))

            OriInsertNav(
                onSave = {
                    taskViewModel.insert(
                        Task(
                            taskId = TaskId,
                            taskName = TaskName,
                            taskDesc = if (TaskDesc == "") null else TaskDesc,
                            taskDue = TaskDue,
                            taskComp = TaskComp
                        )
                    )
                    returnToView()
                },
                onExit = { returnToView() }
            )
        }

        if (showDateDialog) {
            OriDateDialog(
                onDateSelected = {
                    TaskDue = it
                    showDateDialog = false
                },
                selectedDate = TaskDue
            )
        }
    }
}